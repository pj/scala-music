package nz.kiwi.johnson.scalam

// tempos
case class Tempo(val bpm: Int) extends PassagePart

object Tempo {
  implicit class PimpedTempoInt(bpm: Int) {
    def bpm: Tempo = {
      new Tempo(bpm)
    }
  }
}

object largo extends Tempo(60)
object adagio extends Tempo(96)
object presto extends Tempo(120)

// time signatures
case class Signature(val beats: Int, val division: Int) extends PassagePart

object Signature {
  implicit class PimpedSignature(beats: Int) {
    def -/-(division: Int) = {
      new Signature(beats, division)
    }
  }
}