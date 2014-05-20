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
  // start a line of a set length
   def |(note: Note): LineHeader = {
     new LineHeader(this, Seq(note))
   }
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

class dotted(val length: Length, val dots: Int) extends Length

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
  
  private def length(newlength: Length) {
    new Note(octave, note, newlength, velocity, velocityChange, pitchChange)
  }
  
  private def dotted(newlength: Length, dots: Int) {
    new Note(octave, note, new dotted(newlength, dots), velocity, velocityChange, pitchChange)
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
  def *   = dotted(length, 1)
  def **  = dotted(length, 2)
  def *** = dotted(length, 3)
   
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
  def b    = flat
  def |#    = sharp
}

class NullNote extends Note(0, 0, UnknownLength) {}
object ___ extends NullNote {}

// tracker syntax
class Line(length: Length, notes: Seq[Note])

class LineHeader(length: Length, notes: Seq[Note]) {
   def |(note: Note): LineHeader = {
     new LineHeader(length, this.notes :+ note)
   }
   
   def || : LineHeader = {
     this
   }
}

class PassageHeader(val instruments: Seq[Instrument]) {
   def |(instrument: Instrument): PassageHeader = {
     new PassageHeader(this.instruments :+ instrument)
   }
   
   def || : PassageHeader = {
     this
   }
}

class Passage(val tempo: tempo, val signature: signature, 
    val instruments: Seq[Instrument], val lines: Seq[LineHeader]) {
}

object PassageBuilder {
  // start a new passage
  def passage(bpm: tempo, signature: signature)(passageHeader: PassageHeader, lineHeaders: LineHeader*): Passage = {
    new Passage(bpm, signature, passageHeader.instruments, lineHeaders)
  }
  
  // new passage header
  def instruments = {
    new PassageHeader(Seq())
  }
} 

// drum syntax