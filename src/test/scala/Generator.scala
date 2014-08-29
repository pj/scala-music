
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
  
  "Thing" should "generate stuff" in {
    def getMidiNote(octave: Int, note: Char, accidental: String): Int = {
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
      
      12 + (octave * 12) + noteIndex + noteAccidental
    }
    
    def valid(note: Char, accidental: String): Boolean = {
      !((Seq('B', 'E').contains(note) && accidental == "s") 
          || (Seq('C', 'F').contains(note) && accidental == "b"))  
    }
    
    printToFile(new File("/Users/pauljohnson/Programming/scala-music/src/main/scala/nz/kiwi/johnson/scalam/Notes.scala")) {
     pw =>
      pw.println("package nz.kiwi.johnson.scalam")
      pw.println("")
      (0 until 9) foreach { octave =>
          ('C' to 'G').toSeq ++ ('A' to 'B').toSeq foreach { note => 
            Seq("b", "", "s") foreach { accidental =>
              if (valid(note, accidental)) {
                val midiNote = getMidiNote(octave, note, accidental)
                pw.println(s"""object $note$octave$accidental extends Note(${octave}, ${midiNote}, UnknownLength)""")
              }
            }
        }
      }
    }
  }
}