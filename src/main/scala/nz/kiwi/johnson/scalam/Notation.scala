package nz.kiwi.johnson.scalam

case class Line(val lineStart: Length, val notes: Seq[Note], val barStart: Boolean) extends PassagePart {
	def replaceNote(note: Note) = {
	  val newNotes = notes.slice(0, -1)
	  new Line(lineStart, newNotes :+ note, barStart)
	}
	
	def addNote(note: Note) = {
	  new Line(lineStart, notes :+ note, barStart)
	}
}

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
class Length(val longName: String) extends BarResultMethods {
  // Used when starting a line 
  def ||(note: Note): LineBuilder = {
    new LineBuilder(new Line(this, Seq(note), false))
  }
  
  // start of a new bar
  def --(note: Note): LineBuilder = {
    new LineBuilder(new Line(this, Seq(note), true))
  }
  
  override def toString() = longName
}

object UnknownLength extends Length("unknown")

object lengths {
  object w extends Length("w")
  object h extends Length("h")
  object q extends Length("q")
  object e extends Length("e")
  object s extends Length("s")
  object whole extends Length("whole")
  object half extends Length("half")
  object quaver extends Length("quaver")
  object eighth extends Length("eighth")
  object semiquaver extends Length("semiquaver")
}

class DottedLength(val length: Length, val dots: Int) extends Length(length.longName)

class Dotted(val dots: Int)

object d extends Dotted(1)
object dd extends Dotted(2)
object ddd extends Dotted(3)

class BarResult(val notes: Seq[Note]) {
  def |(otherNote: Note): BarResult = {
    new BarResult(notes :+ otherNote)
  }
}

object BarResult {
  object | extends BarResult(null)
}

trait BarResultMethods {
  def |(note: Note): BarResult = {
    new BarResult(Seq(note))
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
  def dotted(dots: Int): LineBuilder
  
  def d: LineBuilder   = dotted(1)
  def dd: LineBuilder  = dotted(2)
  def ddd: LineBuilder = dotted(3)
  
  def v(velocity: Int): LineBuilder
  def v(velocity: Int, vc: velocity_change): LineBuilder
  
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
  
  def lengthNormal(newlength: Length): LineBuilder
  def lengthDotted(newLength: Length, dotted: Dotted): LineBuilder
  def lengthBarResult(newLength: Length, bar: BarResult): LineBuilder
  def lengthVelocity(newLength: Length, velocity: Velocity): LineBuilder
  def lengthReturn(newLength: Length, ret: ReturnResult): Line
  
  def w: LineBuilder = lengthNormal(lengths.whole)
  def h: LineBuilder = lengthNormal(lengths.half)
  def q: LineBuilder = lengthNormal(lengths.quaver)
  def e: LineBuilder = lengthNormal(lengths.eighth)
  def s: LineBuilder = lengthNormal(lengths.semiquaver)
  
  def whole: LineBuilder = lengthNormal(lengths.whole)
  def half: LineBuilder = lengthNormal(lengths.half)
  def quaver: LineBuilder = lengthNormal(lengths.quaver)
  def eighth: LineBuilder = lengthNormal(lengths.eighth)
  def semiquaver: LineBuilder = lengthNormal(lengths.semiquaver)
  
  // with dotted
  def w(dotted: Dotted): LineBuilder = lengthDotted(lengths.whole, dotted)
  def h(dotted: Dotted): LineBuilder = lengthDotted(lengths.half, dotted)
  def q(dotted: Dotted): LineBuilder = lengthDotted(lengths.quaver, dotted)
  def e(dotted: Dotted): LineBuilder = lengthDotted(lengths.eighth, dotted)
  def s(dotted: Dotted): LineBuilder = lengthDotted(lengths.semiquaver, dotted)
  
  def whole(dotted: Dotted): LineBuilder = lengthDotted(lengths.whole, dotted)
  def half(dotted: Dotted): LineBuilder = lengthDotted(lengths.half, dotted)
  def quaver(dotted: Dotted): LineBuilder = lengthDotted(lengths.quaver, dotted)
  def eighth(dotted: Dotted): LineBuilder = lengthDotted(lengths.eighth, dotted)
  def semiquaver(dotted: Dotted): LineBuilder = lengthDotted(lengths.semiquaver, dotted) 
  
  // with volume
  def w(velocity: Velocity): LineBuilder = lengthVelocity(lengths.whole, velocity)
  def h(velocity: Velocity): LineBuilder = lengthVelocity(lengths.half, velocity)
  def q(velocity: Velocity): LineBuilder = lengthVelocity(lengths.quaver, velocity)
  def e(velocity: Velocity): LineBuilder = lengthVelocity(lengths.eighth, velocity)
  def s(velocity: Velocity): LineBuilder = lengthVelocity(lengths.semiquaver, velocity)
  
  def whole(velocity: Velocity): LineBuilder = lengthVelocity(lengths.whole, velocity)
  def half(velocity: Velocity): LineBuilder = lengthVelocity(lengths.half, velocity)
  def quaver(velocity: Velocity): LineBuilder = lengthVelocity(lengths.quaver, velocity)
  def eighth(velocity: Velocity): LineBuilder = lengthVelocity(lengths.eighth, velocity)
  def semiquaver(velocity: Velocity): LineBuilder = lengthVelocity(lengths.semiquaver, velocity)
  
  // bar results
  def w(bar: BarResult): LineBuilder = lengthBarResult(lengths.whole, bar)
  def h(bar: BarResult): LineBuilder = lengthBarResult(lengths.half, bar)
  def q(bar: BarResult): LineBuilder = lengthBarResult(lengths.quaver, bar)
  def e(bar: BarResult): LineBuilder = lengthBarResult(lengths.eighth, bar)
  def s(bar: BarResult): LineBuilder = lengthBarResult(lengths.semiquaver, bar)
  
  def whole(bar: BarResult): LineBuilder = lengthBarResult(lengths.whole, bar)
  def half(bar: BarResult): LineBuilder = lengthBarResult(lengths.half, bar)
  def quaver(bar: BarResult): LineBuilder = lengthBarResult(lengths.quaver, bar)
  def eighth(bar: BarResult): LineBuilder = lengthBarResult(lengths.eighth, bar)
  def semiquaver(bar: BarResult): LineBuilder = lengthBarResult(lengths.semiquaver, bar)
  
  // return results
  def w(ret: ReturnResult): Line = lengthReturn(lengths.whole, ret)
  def h(ret: ReturnResult): Line = lengthReturn(lengths.half, ret)
  def q(ret: ReturnResult): Line = lengthReturn(lengths.quaver, ret)
  def e(ret: ReturnResult): Line = lengthReturn(lengths.eighth, ret)
  def s(ret: ReturnResult): Line = lengthReturn(lengths.semiquaver, ret)
  
  def whole(ret: ReturnResult): Line = lengthReturn(lengths.whole, ret)
  def half(ret: ReturnResult): Line = lengthReturn(lengths.half, ret)
  def quaver(ret: ReturnResult): Line = lengthReturn(lengths.quaver, ret)
  def eighth(ret: ReturnResult): Line = lengthReturn(lengths.eighth, ret)
  def semiquaver(ret: ReturnResult): Line = lengthReturn(lengths.semiquaver, ret)
  
  // note methods
  def note(octave: Int, note: Int, length: Length): LineBuilder
  
  def noteBar(octave: Int, note: Int, length: Length, bar: BarResult): LineBuilder
  
  def C4: LineBuilder = note(4, 60, UnknownLength)
  def C4s: LineBuilder = note(4, 61, UnknownLength)
  def D4b: LineBuilder = note(4, 61, UnknownLength)
  def D4: LineBuilder = note(4, 62, UnknownLength) 
  def D4s: LineBuilder = note(4, 63, UnknownLength)
  def E4b: LineBuilder = note(4, 63, UnknownLength)
  def E4: LineBuilder = note(4, 64, UnknownLength)
  def F4: LineBuilder = note(4, 65, UnknownLength)
  def F4s: LineBuilder = note(4, 66, UnknownLength)
  def G4b: LineBuilder = note(4, 66, UnknownLength)
  def G4: LineBuilder = note(4, 67, UnknownLength)
  def G4s: LineBuilder = note(4, 68, UnknownLength)
  def A4b: LineBuilder = note(4, 68, UnknownLength)
  def A4: LineBuilder = note(4, 69, UnknownLength)
  def A4s: LineBuilder = note(4, 70, UnknownLength)
  def B4b: LineBuilder = note(4, 70, UnknownLength)
  def B4: LineBuilder = note(4, 71, UnknownLength)
  
  def C4(length: Length): LineBuilder = note(4, 60, length)
  def C4s(length: Length): LineBuilder = note(4, 61, length)
  def D4b(length: Length): LineBuilder = note(4, 61, length)
  def D4(length: Length): LineBuilder = note(4, 62, length) 
  def D4s(length: Length): LineBuilder = note(4, 63, length)
  def E4b(length: Length): LineBuilder = note(4, 63, length)
  def E4(length: Length): LineBuilder = note(4, 64, length)
  def F4(length: Length): LineBuilder = note(4, 65, length)
  def F4s(length: Length): LineBuilder = note(4, 66, length)
  def G4b(length: Length): LineBuilder = note(4, 66, length)
  def G4(length: Length): LineBuilder = note(4, 67, length)
  def G4s(length: Length): LineBuilder = note(4, 68, length)
  def A4b(length: Length): LineBuilder = note(4, 68, length)
  def A4(length: Length): LineBuilder = note(4, 69, length)
  def A4s(length: Length): LineBuilder = note(4, 70, length)
  def B4b(length: Length): LineBuilder = note(4, 70, length)
  def B4(length: Length): LineBuilder = note(4, 71, length)
  
  def C4(bar: BarResult): LineBuilder = noteBar(4, 60, UnknownLength, bar)
  def C4s(bar: BarResult): LineBuilder = noteBar(4, 61, UnknownLength, bar)
  def D4b(bar: BarResult): LineBuilder = noteBar(4, 61, UnknownLength, bar)
  def D4(bar: BarResult): LineBuilder = noteBar(4, 62, UnknownLength, bar) 
  def D4s(bar: BarResult): LineBuilder = noteBar(4, 63, UnknownLength, bar)
  def E4b(bar: BarResult): LineBuilder = noteBar(4, 63, UnknownLength, bar)
  def E4(bar: BarResult): LineBuilder = noteBar(4, 64, UnknownLength, bar)
  def F4(bar: BarResult): LineBuilder = noteBar(4, 65, UnknownLength, bar)
  def F4s(bar: BarResult): LineBuilder = noteBar(4, 66, UnknownLength, bar)
  def G4b(bar: BarResult): LineBuilder = noteBar(4, 66, UnknownLength, bar)
  def G4(bar: BarResult): LineBuilder = noteBar(4, 67, UnknownLength, bar)
  def G4s(bar: BarResult): LineBuilder = noteBar(4, 68, UnknownLength, bar)
  def A4b(bar: BarResult): LineBuilder = noteBar(4, 68, UnknownLength, bar)
  def A4(bar: BarResult): LineBuilder = noteBar(4, 69, UnknownLength, bar)
  def A4s(bar: BarResult): LineBuilder = noteBar(4, 70, UnknownLength, bar)
  def B4b(bar: BarResult): LineBuilder = noteBar(4, 70, UnknownLength, bar)
  def B4(bar: BarResult): LineBuilder = noteBar(4, 71, UnknownLength, bar)
}

class LineBuilder(line: Line) extends CommonMethods {  
  // note methods
  def note(octave: Int, note: Int, length: Length) = {
    new LineBuilder(line.replaceNote(new Note(octave, note, length)))
  }
  
  def noteBar(octave: Int, note: Int, length: Length, bar: BarResult): LineBuilder = {
    new LineBuilder(line.replaceNote(new Note(octave, note, length)))
  }
  
  // doted
  def dotted(dots: Int) = {
    val note = line.notes.last
    new LineBuilder(line.replaceNote(note.withDotted(dots)))
  }
  
  // velocity methods
  def v(newVelocity: Int) = {
    val note = line.notes.last
    new LineBuilder(line.replaceNote(note.withVelocity(newVelocity)))
  }
  
  // velocities - with changes
  def v(newVelocity: Int, vc: velocity_change) = {
    val note = line.notes.last
    new LineBuilder(line.replaceNote(new Note(note.octave, note.note, note.length, newVelocity, Some(vc), note.pitchChange)))
  }
  
  // normal
  def lengthNormal(newLength: Length) = {
    val note = line.notes.last
    new LineBuilder(line.replaceNote(note.withLength(newLength)))
  }
  
  def lengthDotted(newLength: Length, dotted: Dotted) = {
    val note = line.notes.last
    new LineBuilder(line.replaceNote(note.withLength(newLength).withDotted(dotted)))
  }
  
  def lengthBarResult(newLength: Length, bar: BarResult) = {
    val note = bar.notes.last
    new LineBuilder(line.replaceNote(note.withLength(newLength)))
  }
  
  def lengthVelocity(newLength: Length, velocity: Velocity) = {
    val note = line.notes.last
    new LineBuilder(line.replaceNote(note.withLength(newLength).withVelocity(velocity)))
  }
  
  def lengthReturn(newLength: Length, ret: ReturnResult) = {
    val note = line.notes.last
    line.replaceNote(note.withLength(newLength))
  }
  
  def ||(): Line = {
    line
  }
  
  def |(nextNote: Note): LineBuilder = {
    new LineBuilder(line.addNote(nextNote))
  }
}

class Note(val octave: Int, val note: Int, val length: Length, val velocity: Int = 127, 
           val velocityChange: Option[velocity_change] = None, val pitchChange: Option[pitch_change] = None){
  
  override def toString() = {
    s"$note $length $velocity"
  }
//            extends CommonMethods {
  
//  def dotted(dots: Int) = {
//    new LineBuilder(Seq(new Note(octave, note, new DottedLength(length, dots), velocity, velocityChange, pitchChange)))
//  }
//  
//  def lengthNormal(newLength: Length) = {
//    new LineBuilder(Seq(new Note(octave, note, newLength, velocity, velocityChange, pitchChange)))
//  }
//  
//  def lengthDotted(newLength: Length, dotted: Dotted) = {
//    new LineBuilder(Seq(new Note(octave, note, new DottedLength(newLength, dotted.dots), velocity, velocityChange, pitchChange)))
//  }
//  
//  def lengthVelocity(newLength: Length, velocity: Velocity) = {
//    new LineBuilder(Seq(new Note(octave, note, newLength, velocity.volume, velocityChange, pitchChange)))
//  }
//  
//  def lengthBarResult(newLength: Length, bar: BarResult) = {
//    val note = bar.notes.last
//    new LineBuilder(Seq(this, new Note(note.octave, note.note, newLength, note.velocity, note.velocityChange, note.pitchChange)))
//  }
//  
//  def lengthReturn(newLength: Length, ret: ReturnResult) = {
//    Seq(new Note(octave, note, newLength, velocity, velocityChange, pitchChange))
//  }
  
  def withLength(newLength: Length): Note = {
    new Note(octave, note, newLength, velocity, velocityChange, pitchChange)
  }
  
  def withVelocity(newVelocity: Velocity): Note = {
    new Note(octave, note, length, newVelocity.volume, velocityChange, pitchChange)
  }
  
  def withVelocity(newVelocity: Int): Note = {
    new Note(octave, note, length, newVelocity, velocityChange, pitchChange)
  }
  
  def withDotted(dotted: Dotted): Note = {
    new Note(octave, note, new DottedLength(length, dotted.dots), velocity, velocityChange, pitchChange)
  }
  
  def withDotted(dots: Int): Note = {
    new Note(octave, note, new DottedLength(length, dots), velocity, velocityChange, pitchChange)
  }
}

object ___ extends Note(-1, -1, null)

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