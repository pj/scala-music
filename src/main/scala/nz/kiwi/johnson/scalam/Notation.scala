package nz.kiwi.johnson.scalam

// tempos
class tempo(val bpm: Int)

object tempo {
  implicit class PimpedTempoInt(bpm: Int) {
    def bpm: tempo = {
      new tempo(bpm)
    }
  }
}

object adagio extends tempo(96)
object presto extends tempo(120)

// time signatures
class signature(val beats: Int, val division: Int)

object signature {
  implicit class PimpedSignature(beats: Int) {
    def -/-(division: Int) = {
      new signature(beats, division)
    }
  }
}

// lengths
class Length {
}

object UnknownLength extends Length

object lengths {
  object w extends Length
  object h extends Length
  object q extends Length
  object e extends Length
  object s extends Length
  object whole extends Length
  object half extends Length
  object quaver extends Length
  object eighth extends Length
  object semiquaver extends Length
}

class Dotted(underlying: Length, dots: Int) extends Length {
  
}

// volume changes
sealed trait volume_change

object volume_change {
  object cres extends volume_change
  object decr extends volume_change
}

// pitch change
sealed trait pitch_change

object pitch_change {
  object port extends pitch_change
  object gliss extends pitch_change
}
class Note(val octave: Int, val note: Int, val length: Length, val velocity: Int = 127, 
           val velocityChange: Option[volume_change] = None, val pitchChange: Option[pitch_change] = None) {
  // return copies of the note with additional options
  def apply(newVelocity: Int): Note = {
    new Note(octave, note, length, newVelocity, velocityChange, pitchChange)
  }
  
  def apply(newVelocity: Int, newVelocityChange: volume_change, newPitchChange: pitch_change): Note = {
    new Note(octave, note, length, newVelocity, Some(newVelocityChange), Some(newPitchChange))
  }
  
  private def length(newlength: Length) = {
    new Note(octave, note, newlength, velocity, velocityChange, pitchChange)
  }
  
  private def dotted(dots: Int) = {
    new Note(octave, note, new Dotted(length, dots), velocity, velocityChange, pitchChange)
  }
  
  // length methods
  def w = length(lengths.whole)
  def h = length(lengths.half)
  def q = length(lengths.quaver)
  def e = length(lengths.eighth)
  def s = length(lengths.semiquaver)
  
  def whole = length(lengths.whole)
  def half = length(lengths.half)
  def quaver = length(lengths.quaver)
  def eighth = length(lengths.eighth)
  def semiquaver = length(lengths.semiquaver)
  
  // dotted
  def *   = dotted(1)
  def **  = dotted(2)
  def *** = dotted(3)
   
  // velocities
  def cres = new Note(octave, note, length, velocity, Some(volume_change.cres), pitchChange)
  def decr = new Note(octave, note, length, velocity, Some(volume_change.decr), pitchChange)
  
  def crescendo = new Note(octave, note, length, velocity, Some(volume_change.cres), pitchChange)
  def decrescendo = new Note(octave, note, length, velocity, Some(volume_change.decr), pitchChange)
  
  // volumes
  def ppp  = new Note(octave, note, length, 16, velocityChange, pitchChange)
  def pp   = new Note(octave, note, length, 33, velocityChange, pitchChange)
  def p    = new Note(octave, note, length, 49, velocityChange, pitchChange) 
  def mp   = new Note(octave, note, length, 64, velocityChange, pitchChange) 
  def mf   = new Note(octave, note, length, 80, velocityChange, pitchChange) 
  def f    = new Note(octave, note, length, 96, velocityChange, pitchChange) 
  def ff   = new Note(octave, note, length, 112, velocityChange, pitchChange) 
  def fff  = new Note(octave, note, length, 126, velocityChange, pitchChange) 
  
  def v(newVelocity: Int) = new Note(octave, note, length, newVelocity, velocityChange, pitchChange)
  
  // pitch
  def gliss = new Note(octave, note, length, velocity, velocityChange, Some(pitch_change.gliss))
  def port = new Note(octave, note, length, velocity, Some(volume_change.decr), pitchChange)
  
  def sharp = new Note(octave, note+1, length, velocity, velocityChange, pitchChange)
  def flat  = new Note(octave, note-1, length, velocity, velocityChange, pitchChange)
  def b     = flat
  def sh    = sharp
}

class NullNote extends Note(0, 0, UnknownLength) {}

object ___ extends NullNote

class Line(length: Length, val notes: Seq[Note])

object LineStarters {
  private def length(newlength: Length, notes: Seq[Note]) = {
    new Line(newlength, notes)
  }
  
  // length methods
  def w(notes: Note*) = length(lengths.whole, notes)
  def h(notes: Note*) = length(lengths.half, notes)
  def q(notes: Note*) = length(lengths.quaver, notes)
  def e(notes: Note*) = length(lengths.eighth, notes)
  def s(notes: Note*) = length(lengths.semiquaver, notes)
  
  def whole(notes: Note*) = length(lengths.whole, notes)
  def half(notes: Note*) = length(lengths.half, notes)
  def quaver(notes: Note*) = length(lengths.quaver, notes)
  def eighth(notes: Note*) = length(lengths.eighth, notes)
  def semiquaver(notes: Note*) = length(lengths.semiquaver, notes)
}

class Passage(val tempo: tempo, val signature: signature, 
    val instruments: Seq[Instrument], val lines: Seq[Line]) {
}

class NotationException(msg: String) extends Exception(msg)

object Passage {
  // start a new passage
  def apply(bpm: tempo, signature: signature, instruments: Seq[Instrument], lines: Line*): Passage = {
    // verify that the lines and instruments are the same size
    lines.zipWithIndex.foreach {
      case (line, idx) =>
        if (line.notes.length != instruments.length) {
          throw new NotationException("Number of notes doesn't equal number of instruments at line " + idx)
        }
    }
    
    new Passage(bpm, signature, instruments, lines)
  }
  
  def passage(bpm: tempo, signature: signature, instruments: Seq[Instrument], lines: Line*): Passage = {
    apply(bpm, signature, instruments, lines: _*)
  }
  
  def instruments(instruments: Instrument*) = {
    instruments
  }
}