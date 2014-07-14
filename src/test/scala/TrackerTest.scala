import org.scalatest.FlatSpec
import org.scalatest.Matchers
import java.io.File
import nz.kiwi.johnson.scalam.Instruments._
import scala.collection.JavaConversions._
import javax.sound.midi._
import java.util.ServiceLoader
import javax.sound.midi.spi.MidiDeviceProvider
import java.io.FileInputStream
import nz.kiwi.johnson.scalam.MidiUtils.PimpedMidiDevice
import nz.kiwi.johnson.scalam.tempo.PimpedTempoInt
import nz.kiwi.johnson.scalam.signature.PimpedSignature
import nz.kiwi.johnson.scalam.MidiUtils
import nz.kiwi.johnson.scalam.Passage.passage
import nz.kiwi.johnson.scalam.Passage.instruments
import nz.kiwi.johnson.scalam.LineStarters._
import nz.kiwi.johnson.scalam._

class TrackerTest extends FlatSpec with Matchers {  
  "Thing" should "generate stuff" in {
    val test = passage(
                  100 bpm, 
                  4-/-4, 
                  instruments(AcousticGrandPiano, AcousticGrandPiano, AcousticGrandPiano),   
                  q(A4, C4, E4),
                  q(C4, E4, G4),
                  q(E4, G4, A5),
                  q(A4, C4, E4)
                )
    
    println(test)

    var sequencer: Sequencer = MidiUtils.getSequencers()(0)
    var synthesizer: Synthesizer = MidiUtils.getSynthesizers()(0)
    
    val file = new FileInputStream("/Users/pauljohnson/Downloads/AccumulaTown.mid")
    
    sequencer.setSequence(file)
   
    sequencer >> synthesizer
    
    sequencer.start()
    
    Thread.sleep(5000) 
  }
}