package nz.kiwi.johnson.scalam

// tempos
class Tempo(val bpm: Int) extends PassagePart

object Tempo {
  implicit class PimpedTempoInt(bpm: Int) {
    def bpm: Tempo = {
      new Tempo(bpm)
    }
  }
}

object adagio extends Tempo(96)
object presto extends Tempo(120)
object largo extends Tempo(60)

// time signatures
class Signature(val beats: Int, val division: Int) extends PassagePart

object Signature {
  implicit class PimpedSignature(beats: Int) {
    def -/-(division: Int) = {
      new Signature(beats, division)
    }
  }
}