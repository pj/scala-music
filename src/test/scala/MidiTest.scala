import org.scalatest.FlatSpec
import org.scalatest.Matchers
import java.io.File
import nz.kiwi.johnson.scalam._
import scala.collection.JavaConversions._
import javax.sound.midi._
import java.util.ServiceLoader
import javax.sound.midi.spi.MidiDeviceProvider
import java.io.FileInputStream
import nz.kiwi.johnson.scalam.MidiUtils.PimpedMidiDevice
import nz.kiwi.johnson.scalam.MidiUtils
import nz.kiwi.johnson.scalam.MidiUtils.formatMidiEvent
import nz.kiwi.johnson.scalam.Passage.passage
import nz.kiwi.johnson.scalam._

import nz.kiwi.johnson.scalam.velocities._
import nz.kiwi.johnson.scalam.ReturnResult._
import nz.kiwi.johnson.scalam.BarResult
import nz.kiwi.johnson.scalam.lengths._
import nz.kiwi.johnson.scalam._
import nz.kiwi.johnson.scalam.Passage._
import nz.kiwi.johnson.scalam.Instrument._

import nz.kiwi.johnson.scalam.Signature.PimpedSignature

class MidiTest extends FlatSpec with Matchers {  
	"Midi utils" should "generate a list of note events properly" in {
	  // Create passages
      val p44 = passage(
              adagio, 
              4-/-4,
    		  inst || AcousticGrandPiano ||,
    		  q    -- A4 quaver          ||,
    		  q    || C4 quaver          ||,
    		  q    || E4 quaver          ||,
    		  q    || A4 quaver          ||
    		  )
      val p34 = passage(largo,
    		  3-/-4,
    		  inst || Celesta            ||,
    		  q    -- A4 quaver          ||,
    		  q    || C4 quaver          ||,
    		  q    || E4 quaver          ||
              )
              
     // Sequence passages
     val sequence = p44 | p34
     
     // generate midisequence for playback.         
     val midiSequence = MidiUtils.sequenceToMidiSequence(sequence)
     
     midiSequence.getTracks().foreach {
        track =>
          if (track.size > 1) {
            println(track)
          
            Range(0, track.size()).foreach {
              number => println(formatMidiEvent(track.get(number)))
            }
          }
      }
	}
}