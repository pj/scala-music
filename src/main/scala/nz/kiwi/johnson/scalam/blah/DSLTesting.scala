package nz.kiwi.johnson.scalam.blah

// volume changes
sealed trait velocity_change

object velocity_change {
  class crescendo extends velocity_change
  class decrescendo extends velocity_change
  object crescendo extends crescendo
  object decrescendo extends decrescendo
  object cres extends crescendo
  object decr extends decrescendo
}

// pitch change
sealed trait pitch_change

object pitch_change {
  object port extends pitch_change
  object gliss extends pitch_change
}

// lengths
trait Length

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

trait LengthMethods[T] {
  def lengthNormal(length: Length): T
  def lengthDotted(length: Length, dotted: Dotted): T
  def lengthVelocity(length: Length, velocity: Velocity): T
  def lengthBarResult(bar: BarResult): T
  def lengthReturn(ret: ReturnResult): Seq[Note]
  
  // normal
  def w: T = lengthNormal(lengths.whole)
  def h: T = lengthNormal(lengths.half)
  def q: T = lengthNormal(lengths.quaver)
  def e: T = lengthNormal(lengths.eighth)
  def s: T = lengthNormal(lengths.semiquaver)
  
  def whole: T = lengthNormal(lengths.whole)
  def half: T = lengthNormal(lengths.half)
  def quaver: T = lengthNormal(lengths.quaver)
  def eighth: T = lengthNormal(lengths.eighth)
  def semiquaver: T = lengthNormal(lengths.semiquaver)
  
  // with dotted
  def w(dotted: Dotted): T = lengthDotted(lengths.whole, dotted)
  def h(dotted: Dotted): T = lengthDotted(lengths.half, dotted)
  def q(dotted: Dotted): T = lengthDotted(lengths.quaver, dotted)
  def e(dotted: Dotted): T = lengthDotted(lengths.eighth, dotted)
  def s(dotted: Dotted): T = lengthDotted(lengths.semiquaver, dotted)
  
  def whole(dotted: Dotted): T = lengthDotted(lengths.whole, dotted)
  def half(dotted: Dotted): T = lengthDotted(lengths.half, dotted)
  def quaver(dotted: Dotted): T = lengthDotted(lengths.quaver, dotted)
  def eighth(dotted: Dotted): T = lengthDotted(lengths.eighth, dotted)
  def semiquaver(dotted: Dotted): T = lengthDotted(lengths.semiquaver, dotted) 
  
  // with volume
  def w(velocity: Velocity): T = lengthVelocity(lengths.whole, velocity)
  def h(velocity: Velocity): T = lengthVelocity(lengths.half, velocity)
  def q(velocity: Velocity): T = lengthVelocity(lengths.quaver, velocity)
  def e(velocity: Velocity): T = lengthVelocity(lengths.eighth, velocity)
  def s(velocity: Velocity): T = lengthVelocity(lengths.semiquaver, velocity)
  
  def whole(velocity: Velocity): T = lengthVelocity(lengths.whole, velocity)
  def half(velocity: Velocity): T = lengthVelocity(lengths.half, velocity)
  def quaver(velocity: Velocity): T = lengthVelocity(lengths.quaver, velocity)
  def eighth(velocity: Velocity): T = lengthVelocity(lengths.eighth, velocity)
  def semiquaver(velocity: Velocity): T = lengthVelocity(lengths.semiquaver, velocity)
  
  // bar results
  def w(bar: BarResult): T = lengthBarResult(bar)
  def h(bar: BarResult): T = lengthBarResult(bar)
  def q(bar: BarResult): T = lengthBarResult(bar)
  def e(bar: BarResult): T = lengthBarResult(bar)
  def s(bar: BarResult): T = lengthBarResult(bar)
  
  def whole(bar: BarResult): T = lengthBarResult(bar)
  def half(bar: BarResult): T = lengthBarResult(bar)
  def quaver(bar: BarResult): T = lengthBarResult(bar)
  def eighth(bar: BarResult): T = lengthBarResult(bar)
  def semiquaver(bar: BarResult): T = lengthBarResult(bar)
  
  // return results
  def w(ret: ReturnResult): Seq[Note] = lengthReturn(ret)
  def h(ret: ReturnResult): Seq[Note] = lengthReturn(ret)
  def q(ret: ReturnResult): Seq[Note] = lengthReturn(ret)
  def e(ret: ReturnResult): Seq[Note] = lengthReturn(ret)
  def s(ret: ReturnResult): Seq[Note] = lengthReturn(ret)
  
  def whole(ret: ReturnResult): Seq[Note] = lengthReturn(ret)
  def half(ret: ReturnResult): Seq[Note] = lengthReturn(ret)
  def quaver(ret: ReturnResult): Seq[Note] = lengthReturn(ret)
  def eighth(ret: ReturnResult): Seq[Note] = lengthReturn(ret)
  def semiquaver(ret: ReturnResult): Seq[Note] = lengthReturn(ret)
}

class DottedLength(val length: Length, val dots: Int) extends Length

class Dotted(val dots: Int)

object d extends Dotted(1)
object dd extends Dotted(2)
object ddd extends Dotted(3)

trait DottedMethods[T] {
  def dotted(dots: Int): T
  def d: T   = dotted(1)
  def dd: T  = dotted(2)
  def ddd: T = dotted(3)
}

class BarResult(val note: Note)

object BarResult {
  object | extends BarResult(null)
}

trait BarResultMethods {
  def |(note: Note): BarResult = {
    new BarResult(note)
  }
}

class ReturnResult

object ReturnResult {
  object || extends ReturnResult
}

trait ReturnResultMethods {
  def ||(): ReturnResult = {
    new ReturnResult
  }
}

// velocities
class Velocity(val volume: Int) extends BarResultMethods

object Velocity {
  def velocity(volume: Int) = {
    new Velocity(volume)
  }
  
  def v(volume: Int) = {
    new Velocity(volume)
  }
}

object velocities {
  object ppp extends Velocity(16)
  object pp extends Velocity(33)
  object p extends Velocity(49)
  object mp extends Velocity(64)
  object mf extends Velocity(80)
  object f extends Velocity(96)
  object ff extends Velocity(112)
  object fff extends Velocity(126)
  
  object v {
    def apply(velocity: Int): Velocity = {
      new Velocity(velocity)
    }
  }
}

trait VelocityMethods[T] {
  def v(velocity: Int): T
  def v(velocity: Int, vc: velocity_change): T
  
  // velocities - blank
  def ppp  = v(16)
  def pp   = v(33)
  def p    = v(49)
  def mp   = v(64)
  def mf   = v(80)
  def f    = v(96)
  def ff   = v(112)
  def fff  = v(126)
  
  // with velocity change
  def ppp(vc: velocity_change)  = v(16, vc)
  def pp(vc: velocity_change)   = v(33, vc)
  def p(vc: velocity_change)    = v(49, vc)
  def mp(vc: velocity_change)   = v(64, vc)
  def mf(vc: velocity_change)   = v(80, vc)
  def f(vc: velocity_change)    = v(96, vc)
  def ff(vc: velocity_change)   = v(112, vc)
  def fff(vc: velocity_change)  = v(126, vc)
}

class VelocityChangeResult(notes: Seq[Note])

class VelocityResult(notes: Seq[Note]) extends LengthMethods[VelocityChangeResult]{
  // velocity changes as methods
  def cres = {
    val note = notes.last
    new VelocityChangeResult(Seq(new Note(note.octave, note.note, note.length, note.velocity , Some(velocity_change.cres), note.pitchChange)))
  }
  
  def decr = {
    val note = notes.last
    new VelocityChangeResult(Seq(new Note(note.octave, note.note, note.length, note.velocity , Some(velocity_change.decr), note.pitchChange)))
  }
  
  def crescendo = {
    val note = notes.last
    new VelocityChangeResult(Seq(new Note(note.octave, note.note, note.length, note.velocity , Some(velocity_change.crescendo), note.pitchChange)))
  }
  
  def decrescendo = {
    val note = notes.last
    new VelocityChangeResult(Seq(new Note(note.octave, note.note, note.length, note.velocity , Some(velocity_change.decrescendo), note.pitchChange)))
  }
  
  def lengthNormal(newlength: Length) = {
    val note = notes.last
    new VelocityChangeResult(Seq(new Note(note.octave, note.note, newlength, note.velocity, note.velocityChange, note.pitchChange)))
  }
  
  def lengthDotted(newLength: Length, dotted: Dotted) = {
    val note = notes.last
    new VelocityChangeResult(Seq(new Note(note.octave, note.note, new DottedLength(newLength, dotted.dots), note.velocity, note.velocityChange, note.pitchChange)))
  }
  
  def lengthDotted(newLength: Length, velocity: Velocity) = {
    val note = notes.last
    new VelocityChangeResult(Seq(new Note(note.octave, note.note, newLength, velocity.volume, note.velocityChange, note.pitchChange)))
  }
  
  def lengthBarResult(bar: BarResult) = {
    val note = notes.last
    new VelocityChangeResult(notes :+ bar.note)
  }
}

class NoteResult(notes: Seq[Note]) extends VelocityMethods[VelocityResult] with LengthMethods[VelocityResult] with NoteMethods[VelocityResult] {
  def v(newVelocity: Int) = {
    val note = notes.last
    new VelocityResult(Seq(new Note(note.octave, note.note, note.length, newVelocity, note.velocityChange, note.pitchChange)))
  }
  
  // velocities - with changes
  private def v(newVelocity: Int, vc: velocity_change) = {
    val note = notes.last
    new VelocityResult(Seq(new Note(note.octave, note.note, note.length, newVelocity, Some(vc), note.pitchChange)))
  }
  
  def lengthNormal(newlength: Length) = {
    val note = notes.last
    new VelocityResult(Seq(new Note(note.octave, note.note, newlength, note.velocity, note.velocityChange, note.pitchChange)))
  }
  
  def lengthDotted(newLength: Length, dotted: Dotted) = {
    val note = notes.last
    new VelocityResult(Seq(new Note(note.octave, note.note, new DottedLength(newLength, dotted.dots), note.velocity, note.velocityChange, note.pitchChange)))
  }
  
  def lengthDotted(newLength: Length, velocity: Velocity) = {
    val note = notes.last
    new VelocityResult(Seq(new Note(note.octave, note.note, newLength, velocity.volume, note.velocityChange, note.pitchChange)))
  }
  
  def lengthBarResult(bar: BarResult) = {
    val note = notes.last
    new VelocityResult(notes :+ bar.note)
  }
  
  // note methods
  
  def note(octave: Int, note: Int, length: Length): VelocityResult = {
    new VelocityResult(notes :+ new Note(octave, note, length))
  }
}

trait NoteMethods[T] {
   def note(octave: Int, note: Int, length: Length): T
  
  def C4: T = note(4, 60, UnknownLength)
  def C4s: T = note(4, 61, UnknownLength)
  def D4b: T = note(4, 61, UnknownLength)
  def D4: T = note(4, 62, UnknownLength) 
  def D4s: T = note(4, 63, UnknownLength)
  def E4b: T = note(4, 63, UnknownLength)
  def E4: T = note(4, 64, UnknownLength)
  def F4: T = note(4, 65, UnknownLength)
  def F4s: T = note(4, 66, UnknownLength)
  def G4b: T = note(4, 66, UnknownLength)
  def G4: T = note(4, 67, UnknownLength)
  def G4s: T = note(4, 68, UnknownLength)
  def A4b: T = note(4, 68, UnknownLength)
  def A4: T = note(4, 69, UnknownLength)
  def A4s: T = note(4, 70, UnknownLength)
  def B4b: T = note(4, 70, UnknownLength)
  def B4: T = note(4, 71, UnknownLength)
}

class Note(val octave: Int, val note: Int, val length: Length, val velocity: Int = 127, 
           val velocityChange: Option[velocity_change] = None, val pitchChange: Option[pitch_change] = None)
           extends LengthMethods[NoteResult] with DottedMethods[NoteResult]{
  
  def dotted(dots: Int) = {
    new NoteResult(Seq(new Note(octave, note, new DottedLength(length, dots), velocity, velocityChange, pitchChange)))
  }
  
  def lengthNormal(newlength: Length) = {
    new NoteResult(Seq(new Note(octave, note, newlength, velocity, velocityChange, pitchChange)))
  }
  
  def lengthDotted(newLength: Length, dotted: Dotted) = {
    new NoteResult(Seq(new Note(octave, note, new DottedLength(newLength, dotted.dots), velocity, velocityChange, pitchChange)))
  }
  
  def lengthDotted(newLength: Length, velocity: Velocity) = {
    new NoteResult(Seq(new Note(octave, note, newLength, velocity.volume, velocityChange, pitchChange)))
  }
}

object Test {
  def test {
    import velocities._
    import ReturnResult._
    import BarResult._
    
    //A4 short d loud
    
    //A4 d short loud
    
    // length with dotted and volume
    A4 w d f
    
    // length with volume
    A4 q ppp
    
    // volume change
    // pitch change
    
    // sequence of notes
    //A4 quaver v(23) x A4 quaver pp x C4 quaver
    
    A4 quaver ppp | A4 quaver pp | C4 quaver ||
    
    // sequence with only starting note
    A4 quaver | C4 quaver // | E4 ||   
    
  }
}

object C4 extends Note(4, 60, UnknownLength)
object C4s extends Note(4, 61, UnknownLength)
object D4b extends Note(4, 61, UnknownLength)
object D4 extends Note(4, 62, UnknownLength)
object D4s extends Note(4, 63, UnknownLength)
object E4b extends Note(4, 63, UnknownLength)
object E4 extends Note(4, 64, UnknownLength)
object F4 extends Note(4, 65, UnknownLength)
object F4s extends Note(4, 66, UnknownLength)
object G4b extends Note(4, 66, UnknownLength)
object G4 extends Note(4, 67, UnknownLength)
object G4s extends Note(4, 68, UnknownLength)
object A4b extends Note(4, 68, UnknownLength)
object A4 extends Note(4, 69, UnknownLength)
object A4s extends Note(4, 70, UnknownLength)
object B4b extends Note(4, 70, UnknownLength)
object B4 extends Note(4, 71, UnknownLength)