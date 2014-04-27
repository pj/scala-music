
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import java.io.File

import nz.kiwi.johnson.scalam.Passage.p
import nz.kiwi.johnson.scalam.Instruments._
import nz.kiwi.johnson.scalam.Notes._
import nz.kiwi.johnson.scalam.___

class TrackerTest extends FlatSpec with Matchers {

  "Thing" should "generate stuff" in {
    val passage = p | piano | guitar ||

    passage.q | An4   | An4    ||
//                  q | ___   | ___    ||
//                  q | Bn4   | Bn4    ||
    
    println(passage)
  }
}