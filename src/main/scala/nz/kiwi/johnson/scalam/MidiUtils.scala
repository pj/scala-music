package nz.kiwi.johnson.scalam

import java.util.ServiceLoader
import javax.sound.midi.spi.MidiDeviceProvider
import scala.collection.JavaConversions._
import javax.sound.midi.Synthesizer
import javax.sound.midi.Sequencer
import javax.sound.midi.MidiDevice
import javax.sound.midi.Sequence

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
  
  implicit def passageToSequence(passage: Passage): Sequence = {
    new Sequence(Sequence.PPQ, 24, 16)
  }
}