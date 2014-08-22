package nz.kiwi.johnson.scalam


// A sequence is a collection of collection of passages
class Sequence(val passages: Seq[Seq[Passage]]) {
  
  def <>(otherPassage: Passage) = {
    new Sequence(this.passages :+ Seq(otherPassage))
  } 
    
  def |(otherPassage: Passage) = {
    new Sequence(this.passages :+ Seq(otherPassage))
  }
  
  def |(passages: Seq[Passage]) = {
    new Sequence(this.passages ++ Seq(passages))
  }
  
  def |(sequence: Sequence) = {
    new Sequence(this.passages ++ sequence.passages)
  }
}

class Passage(val tempo: Tempo, val signature: Signature, 
    val instruments: Seq[Instrument], val lines: Seq[PassagePart]) {
  
  def <>(otherPassage: Passage) = {
    new Sequence(Seq(Seq(this, otherPassage)))
  } 
  
  def |(otherPassage: Passage) = {
    new Sequence(Seq(Seq(this), Seq(otherPassage)))
  }
  
  def |(passages: Seq[Passage]) = {
    new Sequence(Seq(Seq(this), passages))
  }
  
  def |(sequence: Sequence) = {
    new Sequence(Seq(this) +: sequence.passages)
  }
}

class NotationException(msg: String) extends Exception(msg)

object Passage {
  // start a new passage
  def apply(bpm: Tempo, signature: Signature, instruments: Seq[Instrument], lines: PassagePart*): Passage = {
    // verify that the lines and instruments are the same size
    lines.zipWithIndex.foreach {
      case (line : Line, idx) =>
        if (line.notes.length != instruments.length) {
          throw new NotationException("Number of notes doesn't equal number of instruments at line " + idx)
        }
      case (tempo: Tempo, idx) => {
        
      }
      case (signature: Signature, idx) => {
        
      }
    }
    
    new Passage(bpm, signature, instruments, lines)
  }
  
  def passage(bpm: Tempo, signature: Signature, instruments: Seq[Instrument], lines: PassagePart*): Passage = {
    apply(bpm, signature, instruments, lines: _*)
  }
  
  def instruments(instruments: Instrument*) = {
    instruments
  }
}