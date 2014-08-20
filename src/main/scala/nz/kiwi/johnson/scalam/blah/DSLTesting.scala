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
trait Length extends BarResultMethods {
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

class DottedLength(val length: Length, val dots: Int) extends Length

class Dotted(val dots: Int)

object d extends Dotted(1)
object dd extends Dotted(2)
object ddd extends Dotted(3)

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

trait CommonMethods {
  def dotted(dots: Int): NoteResult
  
  def d: NoteResult   = dotted(1)
  def dd: NoteResult  = dotted(2)
  def ddd: NoteResult = dotted(3)
  
  def v(velocity: Int): NoteResult
  def v(velocity: Int, vc: velocity_change): NoteResult
  
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
  
  def lengthNormal(newlength: Length): NoteResult
  def lengthDotted(newLength: Length, dotted: Dotted): NoteResult
  def lengthDotted(newLength: Length, velocity: Velocity): NoteResult
  def lengthBarResult(newLength: Length, bar: BarResult): NoteResult
  def lengthVelocity(newLength: Length, velocity: Velocity): NoteResult
  def lengthReturn(newLength: Length, ret: ReturnResult): Seq[Note]
  
  def w: NoteResult = lengthNormal(lengths.whole)
  def h: NoteResult = lengthNormal(lengths.half)
  def q: NoteResult = lengthNormal(lengths.quaver)
  def e: NoteResult = lengthNormal(lengths.eighth)
  def s: NoteResult = lengthNormal(lengths.semiquaver)
  
  def whole: NoteResult = lengthNormal(lengths.whole)
  def half: NoteResult = lengthNormal(lengths.half)
  def quaver: NoteResult = lengthNormal(lengths.quaver)
  def eighth: NoteResult = lengthNormal(lengths.eighth)
  def semiquaver: NoteResult = lengthNormal(lengths.semiquaver)
  
  // with dotted
  def w(dotted: Dotted): NoteResult = lengthDotted(lengths.whole, dotted)
  def h(dotted: Dotted): NoteResult = lengthDotted(lengths.half, dotted)
  def q(dotted: Dotted): NoteResult = lengthDotted(lengths.quaver, dotted)
  def e(dotted: Dotted): NoteResult = lengthDotted(lengths.eighth, dotted)
  def s(dotted: Dotted): NoteResult = lengthDotted(lengths.semiquaver, dotted)
  
  def whole(dotted: Dotted): NoteResult = lengthDotted(lengths.whole, dotted)
  def half(dotted: Dotted): NoteResult = lengthDotted(lengths.half, dotted)
  def quaver(dotted: Dotted): NoteResult = lengthDotted(lengths.quaver, dotted)
  def eighth(dotted: Dotted): NoteResult = lengthDotted(lengths.eighth, dotted)
  def semiquaver(dotted: Dotted): NoteResult = lengthDotted(lengths.semiquaver, dotted) 
  
  // with volume
  def w(velocity: Velocity): NoteResult = lengthVelocity(lengths.whole, velocity)
  def h(velocity: Velocity): NoteResult = lengthVelocity(lengths.half, velocity)
  def q(velocity: Velocity): NoteResult = lengthVelocity(lengths.quaver, velocity)
  def e(velocity: Velocity): NoteResult = lengthVelocity(lengths.eighth, velocity)
  def s(velocity: Velocity): NoteResult = lengthVelocity(lengths.semiquaver, velocity)
  
  def whole(velocity: Velocity): NoteResult = lengthVelocity(lengths.whole, velocity)
  def half(velocity: Velocity): NoteResult = lengthVelocity(lengths.half, velocity)
  def quaver(velocity: Velocity): NoteResult = lengthVelocity(lengths.quaver, velocity)
  def eighth(velocity: Velocity): NoteResult = lengthVelocity(lengths.eighth, velocity)
  def semiquaver(velocity: Velocity): NoteResult = lengthVelocity(lengths.semiquaver, velocity)
  
  // bar results
  def w(bar: BarResult): NoteResult = lengthBarResult(lengths.whole, bar)
  def h(bar: BarResult): NoteResult = lengthBarResult(lengths.half, bar)
  def q(bar: BarResult): NoteResult = lengthBarResult(lengths.quaver, bar)
  def e(bar: BarResult): NoteResult = lengthBarResult(lengths.eighth, bar)
  def s(bar: BarResult): NoteResult = lengthBarResult(lengths.semiquaver, bar)
  
  def whole(bar: BarResult): NoteResult = lengthBarResult(lengths.whole, bar)
  def half(bar: BarResult): NoteResult = lengthBarResult(lengths.half, bar)
  def quaver(bar: BarResult): NoteResult = lengthBarResult(lengths.quaver, bar)
  def eighth(bar: BarResult): NoteResult = lengthBarResult(lengths.eighth, bar)
  def semiquaver(bar: BarResult): NoteResult = lengthBarResult(lengths.semiquaver, bar)
  
  // return results
  def w(ret: ReturnResult): Seq[Note] = lengthReturn(lengths.whole, ret)
  def h(ret: ReturnResult): Seq[Note] = lengthReturn(lengths.half, ret)
  def q(ret: ReturnResult): Seq[Note] = lengthReturn(lengths.quaver, ret)
  def e(ret: ReturnResult): Seq[Note] = lengthReturn(lengths.eighth, ret)
  def s(ret: ReturnResult): Seq[Note] = lengthReturn(lengths.semiquaver, ret)
  
  def whole(ret: ReturnResult): Seq[Note] = lengthReturn(lengths.whole, ret)
  def half(ret: ReturnResult): Seq[Note] = lengthReturn(lengths.half, ret)
  def quaver(ret: ReturnResult): Seq[Note] = lengthReturn(lengths.quaver, ret)
  def eighth(ret: ReturnResult): Seq[Note] = lengthReturn(lengths.eighth, ret)
  def semiquaver(ret: ReturnResult): Seq[Note] = lengthReturn(lengths.semiquaver, ret)
  
  // note methods
  def note(octave: Int, note: Int, length: Length): NoteResult
  
  def noteBar(octave: Int, note: Int, length: Length, bar: BarResult): NoteResult
  
  def C4: NoteResult = note(4, 60, UnknownLength)
  def C4s: NoteResult = note(4, 61, UnknownLength)
  def D4b: NoteResult = note(4, 61, UnknownLength)
  def D4: NoteResult = note(4, 62, UnknownLength) 
  def D4s: NoteResult = note(4, 63, UnknownLength)
  def E4b: NoteResult = note(4, 63, UnknownLength)
  def E4: NoteResult = note(4, 64, UnknownLength)
  def F4: NoteResult = note(4, 65, UnknownLength)
  def F4s: NoteResult = note(4, 66, UnknownLength)
  def G4b: NoteResult = note(4, 66, UnknownLength)
  def G4: NoteResult = note(4, 67, UnknownLength)
  def G4s: NoteResult = note(4, 68, UnknownLength)
  def A4b: NoteResult = note(4, 68, UnknownLength)
  def A4: NoteResult = note(4, 69, UnknownLength)
  def A4s: NoteResult = note(4, 70, UnknownLength)
  def B4b: NoteResult = note(4, 70, UnknownLength)
  def B4: NoteResult = note(4, 71, UnknownLength)
  
  def C4(length: Length): NoteResult = note(4, 60, length)
  def C4s(length: Length): NoteResult = note(4, 61, length)
  def D4b(length: Length): NoteResult = note(4, 61, length)
  def D4(length: Length): NoteResult = note(4, 62, length) 
  def D4s(length: Length): NoteResult = note(4, 63, length)
  def E4b(length: Length): NoteResult = note(4, 63, length)
  def E4(length: Length): NoteResult = note(4, 64, length)
  def F4(length: Length): NoteResult = note(4, 65, length)
  def F4s(length: Length): NoteResult = note(4, 66, length)
  def G4b(length: Length): NoteResult = note(4, 66, length)
  def G4(length: Length): NoteResult = note(4, 67, length)
  def G4s(length: Length): NoteResult = note(4, 68, length)
  def A4b(length: Length): NoteResult = note(4, 68, length)
  def A4(length: Length): NoteResult = note(4, 69, length)
  def A4s(length: Length): NoteResult = note(4, 70, length)
  def B4b(length: Length): NoteResult = note(4, 70, length)
  def B4(length: Length): NoteResult = note(4, 71, length)
  
  def C4(bar: BarResult): NoteResult = noteBar(4, 60, UnknownLength, bar)
  def C4s(bar: BarResult): NoteResult = noteBar(4, 61, UnknownLength, bar)
  def D4b(bar: BarResult): NoteResult = noteBar(4, 61, UnknownLength, bar)
  def D4(bar: BarResult): NoteResult = noteBar(4, 62, UnknownLength, bar) 
  def D4s(bar: BarResult): NoteResult = noteBar(4, 63, UnknownLength, bar)
  def E4b(bar: BarResult): NoteResult = noteBar(4, 63, UnknownLength, bar)
  def E4(bar: BarResult): NoteResult = noteBar(4, 64, UnknownLength, bar)
  def F4(bar: BarResult): NoteResult = noteBar(4, 65, UnknownLength, bar)
  def F4s(bar: BarResult): NoteResult = noteBar(4, 66, UnknownLength, bar)
  def G4b(bar: BarResult): NoteResult = noteBar(4, 66, UnknownLength, bar)
  def G4(bar: BarResult): NoteResult = noteBar(4, 67, UnknownLength, bar)
  def G4s(bar: BarResult): NoteResult = noteBar(4, 68, UnknownLength, bar)
  def A4b(bar: BarResult): NoteResult = noteBar(4, 68, UnknownLength, bar)
  def A4(bar: BarResult): NoteResult = noteBar(4, 69, UnknownLength, bar)
  def A4s(bar: BarResult): NoteResult = noteBar(4, 70, UnknownLength, bar)
  def B4b(bar: BarResult): NoteResult = noteBar(4, 70, UnknownLength, bar)
  def B4(bar: BarResult): NoteResult = noteBar(4, 71, UnknownLength, bar)
}

class NoteResult(notes: Seq[Note]) extends CommonMethods {  
  // note methods
  def note(octave: Int, note: Int, length: Length) = {
    new NoteResult(notes :+ new Note(octave, note, length))
  }
  
  // doted
  def dotted(dots: Int) = {
    val note = notes.last
    new NoteResult(Seq(new Note(note.octave, note.note, new DottedLength(note.length, dots), note.velocity, note.velocityChange, note.pitchChange)))
  }
  
  // velocity methods
  def v(newVelocity: Int) = {
    val note = notes.last
    new NoteResult(Seq(new Note(note.octave, note.note, note.length, newVelocity, note.velocityChange, note.pitchChange)))
  }
  
  // velocities - with changes
  private def v(newVelocity: Int, vc: velocity_change) = {
    val note = notes.last
    new NoteResult(Seq(new Note(note.octave, note.note, note.length, newVelocity, Some(vc), note.pitchChange)))
  }
  
  // normal
  def lengthNormal(newlength: Length) = {
    val note = notes.last
    new NoteResult(notes :+ new Note(note.octave, note.note, newlength, note.velocity, note.velocityChange, note.pitchChange))
  }
  
  def lengthDotted(newLength: Length, dotted: Dotted) = {
    val note = notes.last
    new NoteResult(notes :+ new Note(note.octave, note.note, new DottedLength(newLength, dotted.dots), note.velocity, note.velocityChange, note.pitchChange))
  }
  
  def lengthDotted(newLength: Length, velocity: Velocity) = {
    val note = notes.last
    new NoteResult(notes :+ new Note(note.octave, note.note, newLength, velocity.volume, note.velocityChange, note.pitchChange))
  }
  
  def lengthBarResult(newLength: Length, bar: BarResult) = {
    val note = bar.note
    new NoteResult(notes :+ new Note(note.octave, note.note, newLength, note.velocity, note.velocityChange, note.pitchChange))
  }
  
  def lengthVelocity(newLength: Length, velocity: Velocity) = {
    val note = notes.last
    new NoteResult(notes :+ new Note(note.octave, note.note, newLength, velocity.volume, note.velocityChange, note.pitchChange))
  }
  
  def lengthReturn(newLength: Length, ret: ReturnResult) = {
    val note = notes.last
    notes :+ new Note(note.octave, note.note, newLength, note.velocity, note.velocityChange, note.pitchChange)
  }
  
  def ||(): Seq[Note] = {
    notes
  }
}

class Note(val octave: Int, val note: Int, val length: Length, val velocity: Int = 127, 
           val velocityChange: Option[velocity_change] = None, val pitchChange: Option[pitch_change] = None) 
            extends CommonMethods {
  
  def dotted(dots: Int) = {
    new NoteResult(Seq(new Note(octave, note, new DottedLength(length, dots), velocity, velocityChange, pitchChange)))
  }
  
  def lengthNormal(newLength: Length) = {
    new NoteResult(Seq(new Note(octave, note, newLength, velocity, velocityChange, pitchChange)))
  }
  
  def lengthDotted(newLength: Length, dotted: Dotted) = {
    new NoteResult(Seq(new Note(octave, note, new DottedLength(newLength, dotted.dots), velocity, velocityChange, pitchChange)))
  }
  
  def lengthVelocity(newLength: Length, velocity: Velocity) = {
    new NoteResult(Seq(new Note(octave, note, newLength, velocity.volume, velocityChange, pitchChange)))
  }
  
  def lengthBarResult(newLength: Length, bar: BarResult) = {
    val note = bar.note
    new NoteResult(Seq(this, new Note(note.octave, note.note, newLength, note.velocity, note.velocityChange, note.pitchChange)))
  }
  
  def lengthReturn(newLength: Length, ret: ReturnResult) = {
    Seq(new Note(octave, note, newLength, velocity, velocityChange, pitchChange))
  }
}

object Test {
  def test {
    import velocities._
    import ReturnResult._
    import BarResult._
    import lengths._
    
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
    A4 quaver | C4 quaver | E4 ||  
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