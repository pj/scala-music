package nz.kiwi.johnson.scalam

class NotationException(msg: String) extends Exception(msg)

// Marker trait
trait PassagePart

// A sequence is a collection of collection of passages
class Sequence(val passages: Seq[Passage]) {
  def ++(otherPassage: Passage) = {
    new Sequence(this.passages :+ otherPassage)
  } 
    
  def |(otherPassage: Passage) = {
    new Sequence(this.passages :+ otherPassage)
  }
  
  def |(passages: Seq[Passage]) = {
    new Sequence(this.passages ++ passages)
  }
  
  def |(sequence: Sequence) = {
    new Sequence(this.passages ++ sequence.passages)
  }
  
  override def toString() = {
    val stringedPassages = passages.map {
      passage =>
        passage.toString
    }
    
    stringedPassages.fold(""){
      case (original, next) => original + next
    }
  }
}

// Things are going to be tricky without measuring the maximum line length
class Passage(val name: Option[String], val lines: Seq[PassagePart], val maxLines: Int) {
  
  private[this] def passageMerge(maxLeft: Int, maxRight: Int, leftLines: Seq[PassagePart], rightLines: Seq[PassagePart]): Seq[PassagePart] = {
    val left = leftLines.head
    val right = rightLines.head
    
    val (nextLeftLines, nextRightLines, merged) = (left, right) match {
      case (leftLine: Line, rightLine: Line) => {
        if (leftLine.lineStart != rightLine.lineStart) {
          throw new NotationException("Lines don't have the same start")
        }
        
        if (leftLine.barStart != rightLine.barStart) {
          throw new NotationException("Lines have inconsistent bar starts")
        }
        
        val newLine = new Line(leftLine.lineStart, leftLine.notes ++ rightLine.notes, leftLine.barStart)
        (leftLines.tail, rightLines.tail, newLine)
      }
      
      case (leftTempo: Tempo, rightTempo: Tempo) => {
        if (leftTempo != rightTempo) {
          throw new NotationException("Inconsistent Tempos")
        }
        
        (leftLines.tail, rightLines.tail, leftTempo)
      }
      
      case (leftSignature: Signature, rightSignature: Signature) => {
        if (leftSignature != rightSignature) {
          throw new NotationException("Inconsistent Tempos")
        }
        
        (leftLines.tail, rightLines.tail, leftSignature)
      }
      
      // pad instruments out if they're shorter - not too worried about notes
      case (leftInstrument: InstrumentSequence, rightInstrument: InstrumentSequence) => {
        val newLeft = if (leftInstrument.replaceRight) {
          leftInstrument.instruments ++ Seq.fill[Instrument](maxLeft - leftInstrument.instruments.length)(-->)
        } else {
          leftInstrument.instruments ++ Seq.fill[Instrument](maxLeft - leftInstrument.instruments.length)(_v_)
        }
        
       val newRight = if (rightInstrument.replaceRight) {
          rightInstrument.instruments ++ Seq.fill[Instrument](maxRight - rightInstrument.instruments.length)(-->)
        } else {
          rightInstrument.instruments ++ Seq.fill[Instrument](maxRight - rightInstrument.instruments.length)(_v_)
        }

        val newInstruments = new InstrumentSequence(newLeft ++ newRight)
        (leftLines.tail, rightLines.tail, newInstruments)
      }
      
      // miss-matches - fill in instrument with down?
      case (leftInstrument: InstrumentSequence, _) => {
        val newLeft = if (leftInstrument.replaceRight) {
          leftInstrument.instruments ++ Seq.fill[Instrument](maxLeft - leftInstrument.instruments.length)(-->)
        } else {
          leftInstrument.instruments ++ Seq.fill[Instrument](maxLeft - leftInstrument.instruments.length)(_v_)
        }
        
        val newRight = Seq.fill[Instrument](maxRight)(_v_)
        
        val newInstruments = new InstrumentSequence(newLeft ++ newRight)
        (leftLines.tail, rightLines, newInstruments)
      }
      
      case (_, rightInstrument: InstrumentSequence) => {
       val newRight = if (rightInstrument.replaceRight) {
          rightInstrument.instruments ++ Seq.fill[Instrument](maxRight - rightInstrument.instruments.length)(-->)
        } else {
          rightInstrument.instruments ++ Seq.fill[Instrument](maxRight - rightInstrument.instruments.length)(_v_)
        }
       
        val newLeft = Seq.fill[Instrument](maxLeft)(_v_)
        
        val newInstruments = new InstrumentSequence(newLeft ++ newRight)
        
        (leftLines, rightLines.tail, newInstruments)
      }
        
      case (_, _) => throw new NotationException("Passages don't match")
    }
    
    merged +: passageMerge(maxLeft, maxRight, nextLeftLines, nextRightLines)
  }
  
  // Merge a passage = match time signatures and lengths etc
  def ++(otherPassage: Passage) = {
    val newName = name.getOrElse("") + otherPassage.name.getOrElse("")
    val merged = passageMerge(this.maxLines, otherPassage.maxLines, this.lines, otherPassage.lines)
    new Sequence(Seq(new Passage(Some(newName), merged, this.maxLines + otherPassage.maxLines )))
  } 
  
  def |(otherPassage: Passage) = {
    new Sequence(Seq(this, otherPassage))
  }
  
  def |(passages: Seq[Passage]) = {
    new Sequence(Seq(this) ++ passages)
  }
  
  def |(sequence: Sequence) = {
    new Sequence(Seq(this) ++ sequence.passages)
  }
  
  override def toString(): String = {
    lines.foldLeft("") {
      case (original, line: Line) => {
        original + line.lineStart.toString + " || " + line.notes.foldLeft("") {
          case (combined, note) => combined + note.toString + " | "
        } + "\n"
      }
      case (original, instrumentSequence: InstrumentSequence) => {
        original + instrumentSequence.instruments.foldLeft("") {
          case (combined, instrument) => combined + instrument.name + "\n"
        }
      }
      case (original, Tempo(bpm)) => original + bpm.toString + "\n"
      case (original, signature: Signature) => original + signature.beats.toString + "/" + signature.division.toString + "\n" 
    }
  }
}

object Passage {
  // calculate maximum number of vertical lines - makes it easier to generate instruments
  private[this] def calculateMaxLines(lines: Seq[PassagePart]) = {
    var maxVLines = 0
    
    lines.foreach {
      case instrumentSequence: InstrumentSequence => if (instrumentSequence.instruments.length > maxVLines) {
        maxVLines = instrumentSequence.instruments.length
      }
      case line: Line => if (line.notes.length > maxVLines) {
        maxVLines = line.notes.length
      }
      case _ => 
	}
    
    maxVLines
  }
  
  def apply(lines: PassagePart*): Passage = {	  
    new Passage(None, lines, calculateMaxLines(lines))
  }
  
  def apply(name: String, lines: PassagePart*): Passage = {
    new Passage(Some(name), lines, calculateMaxLines(lines))
  }
  
  def passage(lines: PassagePart*): Passage = {
    apply(lines: _*)
  }
  
  def passage(name: String, lines: PassagePart*): Passage = {
    apply(name, lines: _*)
  }
}