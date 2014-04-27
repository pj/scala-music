
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import java.io.File
import org.scalatest.Ignore

@Ignore
class Generator extends FlatSpec with Matchers {
  def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit) {
    val p = new java.io.PrintWriter(f)
    try { op(p) } finally { p.close() }
  }

//  val semimap = Map(1 -> Seq("An"), 2 -> Seq("As")
  
  "Thing" should "generate stuff" in {
    printToFile(new File("/Users/pauljohnson/Programming/scala-music/src/main/scala/nz/kiwi/johnson/scalam/Notes.scala")) {
     pw =>
      pw.println("package nz.kiwi.johnson.scalam")
//      pw.println("sealed trait Note")
//      pw.println("sealed trait Chord")
//      pw.println("sealed trait Chord")
      pw.println("")
      pw.println("package object Notes {")
      (0 until 9) foreach { octave =>
          ('A' to 'G') foreach { note => 
              Seq('f', 'n', 's') foreach { accidental => 
                pw.println(s"""case class $note$accidental$octave() extends Note { val octave = ${octave} ; val note = "${note}"; val accidental = "${accidental}" }""")
//                  Seq('W', 'H', 'Q', 'E', 'S') foreach { length =>  
                  // note
//                  pw.println(s"""case class ${note}${accidental}${length}${octave} extends Note { val octave = ${octave} ; val note = "${note}"; val length = "${length}"; val accidental = "${accidental}" }""")
                  
                  
                  // chords
//                  Seq("", "min", "7", "min7", "maj7") foreach { chord =>
//                    
//                  }
//                }
            }
        }
      }
      
      pw.println("}")
//      }
    }
  }
}