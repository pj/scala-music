package nz.kiwi.johnson.scalam

import java.util.ServiceLoader
import javax.sound.midi.spi.MidiDeviceProvider
import scala.collection.JavaConversions._
import javax.sound.midi.Synthesizer
import javax.sound.midi.Sequencer
import javax.sound.midi.MidiDevice
import javax.sound.midi.{Sequence => MidiSequence}
import javax.sound.midi.MidiEvent
import javax.sound.midi.ShortMessage
import scala.collection.mutable
import javax.sound.midi.Track
import scala.collection.mutable.ArrayBuffer
import nz.kiwi.johnson.scalam.Line

object MidiUtils {
  val serviceLoaders = ServiceLoader.load(classOf[MidiDeviceProvider]).toSeq
  
  def getDeviceType[A](typeChecker: MidiDevice => Option[A]): Seq[A] = {
    serviceLoaders flatMap {
      deviceProvider =>
        val deviceInfos = deviceProvider.getDeviceInfo()
        
        deviceInfos flatMap {
          deviceInfo => 
            val device = deviceProvider.getDevice(deviceInfo)
            
            device.open
            
            typeChecker(device)
        }
    }
  }
  
  def getSynthesizers() = {
    getDeviceType[Synthesizer] {
      case device: Synthesizer => Some(device)
      case _        => None
    }
  }
  
  def getSequencers() = {
    getDeviceType[Sequencer] {
      case device: Sequencer => Some(device)
      case _        => None
    }
  }
  
  implicit class PimpedMidiDevice(device: MidiDevice) {
    def >>(otherDevice: MidiDevice) = {
      val transmitter = device.getTransmitter()
      val receiver = otherDevice.getReceiver()
    
      transmitter.setReceiver(receiver)
    }
  }
  
  implicit class PimpedMidiSequnce(sequence: MidiSequence) {
    def >>(sequencer: Sequencer) = {
      sequencer.setSequence(sequence)
    }
  }
  
  implicit class PimpedSequnce(sequence: Sequence) {
    def >>(sequencer: Sequencer) = {
      sequencer.setSequence(sequence)
    }
  }
  
  def getMidiNoteNumber(octave: Int, note: Char, accidental: String): Int = {
	  // A0 is 21
	  val noteIndex = note match {
	    case 'A' => 9
	    case 'B' => 11
	    case 'C' => 0
	    case 'D' => 2
	    case 'E' => 4
	    case 'F' => 5
	    case 'G' => 7
	  }
	  
	  val noteAccidental = accidental match {
	    case "s" => 1
	    case ""  => 0
	    case "b" => -1
	  }
	  
	  12 + (octave * 12) + (noteIndex + noteAccidental)
  }
  
  // TODO: Add support for keys to ease printing
  def getMidiNoteString(noteNumber: Int, key: Int = 0): String = {
    val octave = (noteNumber - 12)  / 12
    val note = (noteNumber - 12) % 12
    
    val baseNote = note match {
	    case 0 => "C"
	    case 1 => "C#"
	    case 2 => "D"
	    case 3 => "D#"
	    case 4 => "E"
	    case 5 => "F"
	    case 6 => "F#"
	    case 7 => "G"
	    case 8 => "G#"
	    case 9 => "A"
	    case 10 => "A#"
	    case 11 => "B"
	  } 
    
    baseNote + octave.toString
  }
  
  // Pretty midi event printer
  def formatMidiEvent(event: MidiEvent): String = {
	  event.getMessage() match {
	    case shortMessage: ShortMessage => {
	      shortMessage.getCommand() match {
	        case ShortMessage.NOTE_ON        => s"Note On: ${getMidiNoteString(shortMessage.getData1())} velocity: ${shortMessage.getData2()} channel: ${shortMessage.getChannel()} time: ${event.getTick()}"
	        case ShortMessage.NOTE_OFF       => s"Note Off: ${getMidiNoteString(shortMessage.getData1())} velocity: ${shortMessage.getData2()} channel: ${shortMessage.getChannel()} time: ${event.getTick()}"
	        case ShortMessage.STOP           => s"Stop: channel: ${shortMessage.getChannel()} time: ${event.getTick()}"
	        case ShortMessage.START          => s"Start: channel: ${shortMessage.getChannel()} time: ${event.getTick()}"
	        case ShortMessage.PROGRAM_CHANGE => s"Program Change: program channel: ${shortMessage.getChannel()} time: ${event.getTick()}"
	        case unknown                     => s"Message: ${shortMessage.getCommand()} channel: ${shortMessage.getChannel()} data1: ${shortMessage.getData1()} data2: ${shortMessage.getData2()}"
	      }
	    }
	    case other => other.toString
	  }
  }
  
  // hides the complexity of updating instruments etc
  class InstrumentMapper {
    // map lines to instruments
    val linesToInstruments = mutable.Map[Int, Instrument]()
    
    // map instruments to a "channel" number - index is the channel number
    val assignedInstruments = mutable.Map[Instrument, Int]()
    
    // most recent changes which need to be updated
    val changedChannels = ArrayBuffer[(Int, Instrument)]()
    
    def clearChanges() {
      changedChannels.clear()
    }
    
    def unassignedChannel(): Int = {
      val assignedChannels = assignedInstruments.values
      
      val possibleChannel = Range(0,256).find {
        index => {
          index % 9 != 0 && !assignedChannels.contains(index)
        }
      }
      
      // set channel to instrument
      possibleChannel match {
        case None => throw new Exception("TODO: Bump up possible channel count")
        case Some(index) => {
        	index
        }
      }
    }
    
    def assignInstrument(line: Int, instrument: Instrument) = {
      linesToInstruments.get(line) match {
        case Some(existingInstrument) => {
          linesToInstruments.put(line, instrument)
          
          // find if this has been assigned to a channel before
          val existingChannel = assignedInstruments.get(instrument)
          
          existingChannel match {
            case Some(channel) => Unit
            case None          => {
            	// find channel
            	val channel = unassignedChannel()
            	assignedInstruments.put(instrument, channel)
            	changedChannels.add((channel, instrument))
            }
          }
           
           // if there are no instances of this line to this instrument then unassign channel
          val foundLine = linesToInstruments.find {
            case (line, instrument) => instrument eq existingInstrument
          }
          
          foundLine match {
            case None => assignedInstruments.remove(existingInstrument)
            case _ => Unit
          }
        }
        case None                     => {
        	// new assignment
        	// assign line to instrument
        	linesToInstruments.put(line, instrument)
        	
        	// find channel
        	val channel = unassignedChannel()
        	
        	assignedInstruments += (instrument -> channel)
        	
        	changedChannels += ((channel, instrument))
        }
      }
    }
    
    def getTrackByLine(lineNumber: Int) = {
      val instrument = linesToInstruments(lineNumber)
      val channel = assignedInstruments(instrument)
      
      val trackNumber = channel / 16
      val trackChannel = channel % 16
        	     
      (trackNumber, trackChannel)
    }
  }
  
  private[this] def assignInstruments(mapper: InstrumentMapper, instruments: Seq[Instrument]) = {
    var previousInstrument = instruments.head
    mapper.clearChanges()
            
    instruments.zipWithIndex.foreach {
      case (`_v_`, index) => {
        // do nothing - previous doesn't change?
      }
      case (`-->`, line) => {
    	mapper.assignInstrument(line, previousInstrument)
      }
      case (instrument: Instrument, line) => {
    	// assign instrument
        mapper.assignInstrument(line, instrument)
        
        previousInstrument = instrument
      }
    }
  }
  
  // Generate a stream of midi events from a sequence or passage
  implicit def sequenceToMidiSequence(sequence: Sequence): MidiSequence = {
	val midiSequence = new MidiSequence(MidiSequence.PPQ, 24, 16)
    
	val tracks = ArrayBuffer[Track]()
    
	var currentTime: Int = 0
    var bpm = 120 
    var beats = 4
    var division = 4
    
    tracks.append(midiSequence.createTrack())
    
	tracks(0).add(new MidiEvent(new ShortMessage(ShortMessage.START), currentTime))
    
    val instrumentMap = new InstrumentMapper()
    
    sequence.passages.foreach {
      passage => 
        passage.lines.foreach {
          case InstrumentSequence(instruments, _) => {
        	assignInstruments(instrumentMap, instruments)
            
            // Issue patch change messages on correct tracks and channels
        	instrumentMap.changedChannels.foreach {
        	   case (channel, instrument) =>
        	     val trackNumber = channel / 16
        	     val trackChannel = channel % 16
        	     
        	     println(channel, trackNumber, trackChannel)
        	     
        	     val track = if (trackNumber < tracks.length ) {
        	       tracks(trackNumber)
        	     } else {
        	       val newTrack = midiSequence.createTrack()
        	       
        	       tracks.append(newTrack)
        	       
        	       newTrack
        	     }
        	     
        	     track.add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, trackChannel, instrument.instrumentNumber), currentTime))
        	}
          }
          case Tempo(newBPM) => {
            bpm = newBPM
          }
          case Signature(newBeats, newDivision) => {
            beats = newBeats
            division = newDivision
          }
          case Line(lineStart, notes, _) => {
             val start = currentTime
             
             var maxLength = start
             
             notes.zipWithIndex.foreach {
               case (note, lineNumber) => 
                 val length = 0
                 val end = start+length
                 val (trackNumber, trackChannel) = instrumentMap.getTrackByLine(lineNumber)
                 val track = tracks(trackNumber)
                 
                 track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, trackChannel, note.note, note.velocity), start))
                 track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, trackChannel, note.note, note.velocity), end))
                 
                 if (end > maxLength) {
                   maxLength = end
                 }
             }
             
             currentTime = start
          }
        }
    }
	
	// TODO: add stop message so playback thread stops
	val track = tracks(0)
	
	track.add(new MidiEvent(new ShortMessage(ShortMessage.STOP), currentTime))
    
    midiSequence
  }
  
  def printServiceLoaders(args: Array[String]): Unit = {
    val serviceLoaders = ServiceLoader.load(classOf[MidiDeviceProvider]);
    
    println(serviceLoaders)
    
    val iterator = serviceLoaders.iterator
    
    while (iterator.hasNext) {
      println(iterator.next)
    }
    
    serviceLoaders.iterator map {
      deviceProvider => 
        println(deviceProvider)
        val deviceInfos = deviceProvider.getDeviceInfo()
        
        deviceInfos map {
          deviceInfo => 
            println(deviceInfo)
        }
    }
  }
}

// Various midi constants
