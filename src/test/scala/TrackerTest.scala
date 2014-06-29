import org.scalatest.FlatSpec
import org.scalatest.Matchers
import java.io.File
import nz.kiwi.johnson.scalam.PassageBuilder._
import nz.kiwi.johnson.scalam.Instruments._
import scala.collection.JavaConversions._
import javax.sound.midi._
import java.util.ServiceLoader
import javax.sound.midi.spi.MidiDeviceProvider
import java.io.FileInputStream
import nz.kiwi.johnson.scalam.MidiUtils.PimpedMidiDevice
import nz.kiwi.johnson.scalam.tempo.PimpedTempoInt
import nz.kiwi.johnson.scalam.signature.PimpedSignature
import nz.kiwi.johnson.scalam.LineStarts._
import nz.kiwi.johnson.scalam.MidiUtils
import nz.kiwi.johnson.scalam.notes._

class TrackerTest extends FlatSpec with Matchers {  
  "Thing" should "generate stuff" in {
    val test = passage(100 bpm, 4-/-4)( 
                  instruments | piano | guitar ||,   
                  q | A4() | A4 ||
//                  w | B5   | B6    ||,
//                  q | C5   | D7    ||,
//                  w | ___  | ___    ||
                  )
    
    println(test)

    var sequencer: Sequencer = MidiUtils.getSequencers()(0)
    var synthesizer: Synthesizer = MidiUtils.getSynthesizers()(0)
    
    val file = new FileInputStream("/Users/pauljohnson/Downloads/AccumulaTown.mid")
    
    sequencer.setSequence(file)
   
    synthesizer >> sequencer
    
    sequencer.start()
    
    Thread.sleep(5000) 
  }
}