
import nz.kiwi.johnson.scalam.velocities
import nz.kiwi.johnson.scalam.ReturnResult
import nz.kiwi.johnson.scalam.BarResult
import nz.kiwi.johnson.scalam.lengths
import nz.kiwi.johnson.scalam._
import nz.kiwi.johnson.scalam.Passage._
import nz.kiwi.johnson.scalam.Instrument._

import nz.kiwi.johnson.scalam.Signature.PimpedSignature

object Test {
  def test {
    import velocities._
    import ReturnResult._
    import BarResult._
    import lengths._
    
    // length with dotted and volume
    q || A4 w d f
    
    // length with volume
    q || A4 q ppp
    
    // volume change
    // pitch change
    
    // sequence of notes
    //A4 quaver v(23) x A4 quaver pp x C4 quaver
    
    q || A4 quaver ppp | A4 quaver pp | C4 quaver ||
    
    q || A4 quaver ppp | ___ | C4 quaver ||
    
    // sequence with only starting note
    q || A4 quaver | C4 quaver | E4 ||  
    
    q || A4 ||
    
    val z = passage(
              adagio, 
              4-/-4,
    		  inst || AcousticGrandPiano | Xylophone ||,
    		  q    || A4 quaver          | C4 quaver ||,
    		  q    || C4 quaver          | E4 quaver ||,
    		  q    || E4 quaver          | G4 quaver ||,
    		  q    || A4                 | C4        ||,
    		  largo,
    		  3-/-4
              )
    
  }
}