package nz.kiwi.johnson.scalam

class Line(length: Length, notes: Seq[Note])

class LineStart(instruments: Seq[Instrument], lines: Seq[Line]) {}

class PassageHeader(val instruments: Seq[Instrument]) {
   def |(instrument: Instrument): PassageHeader = {
     new PassageHeader(this.instruments :+ instrument)
   }
   
   def ||():Passage = {
     new Passage(this.instruments, Seq())
   }
}

class Passage(instruments: Seq[Instrument], lines: Seq[Line]) {
  
  // start a line
  def q(): LineStart = {
    new LineStart(this.instruments, this.lines)
  }
}

object Passage {
  // start a new passage header
  def p: PassageHeader = {
    new PassageHeader(Seq())
  } 
}

class Tracker {
// q | An4q,
// q | Bn4q,
// s |
//
  
  
}