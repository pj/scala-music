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
import nz.kiwi.johnson.scalam.Passage.passage
import nz.kiwi.johnson.scalam.Passage.instruments
import nz.kiwi.johnson.scalam._

class TrackerTest extends FlatSpec with Matchers {  
  "Thing" should "generate stuff" in {
//    var sequence = test1 | (test2 <> test3) | test1 | (test1 <> test2 <> test3)
//
//    // translate to midi even stream
//    
//    var sequencer: Sequencer = MidiUtils.getSequencers()(0)
//    var synthesizer: Synthesizer = MidiUtils.getSynthesizers()(0)
//    
//    val file = new FileInputStream("/Users/pauljohnson/Downloads/AccumulaTown.mid")
//    
//    sequencer.setSequence(file)
//   
//    sequencer >> synthesizer
//    
//    sequencer.start()
//    
//    
//    // TODO: Playback thread
//    
//    Thread.sleep(5000) 
//    
//    1 should not be (7)
  }
}