package nz.kiwi.johnson.scalam

// Purely decorative
object inst {
  def ||(instrument: Instrument): InstrumentSequence = {
    new InstrumentSequence(Seq(instrument))
  }
}

class InstrumentSequence(val instruments: Seq[Instrument]) {
    def |(nextInstrument: Instrument): InstrumentSequence = {
      new InstrumentSequence(instruments :+ nextInstrument)
    }  
    
    def ||(): Seq[Instrument] = {
      return instruments
    }
    
  
    // Piano:
	def AcousticGrandPiano(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.AcousticGrandPiano)
	def BrightAcousticPiano(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.BrightAcousticPiano)
	def ElectricGrandPiano(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.ElectricGrandPiano)
	def HonkytonkPiano(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.HonkytonkPiano)
	def ElectricPiano1(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.ElectricPiano1)
	def ElectricPiano2(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.ElectricPiano2)
	def Harpsichord(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Harpsichord)
	def Clavinet(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Clavinet)

	// Chromatic Percussion:
	def Celesta(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Celesta)
	def Glockenspiel(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Glockenspiel)
	def MusicBox(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.MusicBox)
	def Vibraphone(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Vibraphone)
	def Marimba(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Marimba)
	def Xylophone(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Xylophone)
	def TubularBells(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.TubularBells)
	def Dulcimer(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Dulcimer)
	
	// Organ:
	def DrawbarOrgan(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.DrawbarOrgan)
	def PercussiveOrgan(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.PercussiveOrgan)
	def RockOrgan(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.RockOrgan)
	def ChurchOrgan(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.ChurchOrgan)
	def ReedOrgan(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.ReedOrgan)
	def Accordion(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Accordion)
	def Harmonica(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Harmonica)
	def TangoAccordion(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.TangoAccordion)
	
	// Guitar:
	def AcousticGuitarNylon(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.AcousticGuitarNylon)
	def AcousticGuitarSteel(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.AcousticGuitarSteel)
	def ElectricGuitarJazz(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.ElectricGuitarJazz)
	def ElectricGuitarClean(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.ElectricGuitarClean)
	def ElectricGuitarMuted(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.ElectricGuitarMuted)
	def OverdrivenGuitar(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.OverdrivenGuitar)
	def DistortionGuitar(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.DistortionGuitar)
	def Guitarharmonics(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Guitarharmonics)
	
	// Bass:
	def AcousticBass(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.AcousticBass)
	def ElectricBassFinger(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.ElectricBassFinger)
	def ElectricBassPick(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.ElectricBassPick)
	def FretlessBass(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.FretlessBass)
	def SlapBass1(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.SlapBass1)
	def SlapBass2(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.SlapBass2)
	def SynthBass1(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.SynthBass1)
	def SynthBass2(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.SynthBass2)
	
	// Strings:
	def Violin(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Violin)
	def Viola(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Viola)
	def Cello(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Cello)
	def Contrabass(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Contrabass)
	def TremoloStrings(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.TremoloStrings)
	def PizzicatoStrings(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.PizzicatoStrings)
	def OrchestralHarp(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.OrchestralHarp)
	def Timpani(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Timpani)
	
	// Strings (continued):
	def StringEnsemble1(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.StringEnsemble1)
	def StringEnsemble2(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.StringEnsemble2)
	def SynthStrings1(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.SynthStrings1)
	def SynthStrings2(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.SynthStrings2)
	def ChoirAahs(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.ChoirAahs)
	def VoiceOohs(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.VoiceOohs)
	def SynthVoice(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.SynthVoice)
	def OrchestraHit(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.OrchestraHit)
	
	// Brass:
	def Trumpet(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Trumpet)
	def Trombone(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Trombone)
	def Tuba(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Tuba)
	def MutedTrumpet(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.MutedTrumpet)
	def FrenchHorn(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.FrenchHorn)
	def BrassSection(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.BrassSection)
	def SynthBrass1(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.SynthBrass1)
	def SynthBrass2(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.SynthBrass2)
	
	// Reed:
	def SopranoSax(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.SopranoSax)
	def AltoSax(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.AltoSax)
	def TenorSax(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.TenorSax)
	def BaritoneSax(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.BaritoneSax)
	def Oboe(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Oboe)
	def EnglishHorn(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.EnglishHorn)
	def Bassoon(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Bassoon)
	def Clarinet(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Clarinet)
	
	// Pipe:
	def Piccolo(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Piccolo)
	def Flute(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Flute)
	def Recorder(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Recorder)
	def PanFlute(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.PanFlute)
	def BlownBottle(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.BlownBottle)
	def Shakuhachi(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Shakuhachi)
	def Whistle(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Whistle)
	def Ocarina(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Ocarina)
	
	// Synth Lead:
	def Lead1Square(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Lead1Square)
	def Lead2Sawtooth(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Lead2Sawtooth)
	def Lead3Calliope(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Lead3Calliope)
	def Lead4Chiff(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Lead4Chiff)
	def Lead5Charang(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Lead5Charang)
	def Lead6Voice(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Lead6Voice)
	def Lead7Fifths(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Lead7Fifths)
	def Lead8BassLead(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Lead8BassLead)
	
	// Synth Pad:
	def Pad1NewAge(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Pad1NewAge)
	def Pad2Warm(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Pad2Warm)
	def Pad3Polysynth(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Pad3Polysynth)
	def Pad4Choir(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Pad4Choir)
	def Pad5Bowed(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Pad5Bowed)
	def Pad6Metallic(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Pad6Metallic)
	def Pad7Halo(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Pad7Halo)
	def Pad8Sweep(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Pad8Sweep)
	
	// Synth Effects:
	def FX1Rain(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.FX1Rain)
	def FX2Soundtrack(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.FX2Soundtrack)
	def FX3Crystal(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.FX3Crystal)
	def FX4Atmosphere(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.FX4Atmosphere)
	def FX5Brightness(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.FX5Brightness)
	def FX6Goblins(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.FX6Goblins)
	def FX7Echoes(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.FX7Echoes)
	def FX8Scifi(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.FX8Scifi)
	
	// Ethnic:
	def Sitar(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Sitar)
	def Banjo(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Banjo)
	def Shamisen(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Shamisen)
	def Koto(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Koto)
	def Kalimba(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Kalimba)
	def Bagpipe(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Bagpipe)
	def Fiddle(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Fiddle)
	def Shanai(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Shanai)
	
	// Percussive:
	def TinkleBell(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.TinkleBell)
	def Agogo(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Agogo)
	def SteelDrums(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.SteelDrums)
	def Woodblock(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Woodblock)
	def TaikoDrum(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.TaikoDrum)
	def MelodicTom(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.MelodicTom)
	def SynthDrum(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.SynthDrum)
	
	// Sound effects:
	def ReverseCymbal(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.ReverseCymbal)
	def GuitarFretNoise(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.GuitarFretNoise)
	def BreathNoise(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.BreathNoise)
	def Seashore(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Seashore)
	def BirdTweet(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.BirdTweet)
	def TelephoneRing(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.TelephoneRing)
	def Helicopter(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Helicopter)
	def Applause(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Applause)
	def Gunshot(): InstrumentSequence = new InstrumentSequence(instruments :+ Instrument.Gunshot) 
}

case class Instrument(instrumentNumber: Int, name: String) {
    def |(nextInst: Instrument): InstrumentSequence = {
      new InstrumentSequence(Seq(nextInst))
    }
}

// ditto instrument
object rep extends Instrument(-1, "Ditto")

object Instrument {
// Piano:
object AcousticGrandPiano extends Instrument(1, "Acoustic Grand Piano") {}
object BrightAcousticPiano extends Instrument(2, "Bright Acoustic Piano") {}
object ElectricGrandPiano extends Instrument(3, "Electric Grand Piano") {}
object HonkytonkPiano extends Instrument(4, "Honky-tonk Piano") {}
object ElectricPiano1 extends Instrument(5, "Electric Piano 1") {}
object ElectricPiano2 extends Instrument(6, "Electric Piano 2") {}
object Harpsichord extends Instrument(7, "Harpsichord") {}
object Clavinet extends Instrument(8, "Clavinet") {}

// Chromatic Percussion:
object Celesta extends Instrument(9, "Celesta") {}
object Glockenspiel extends Instrument(10, "Glockenspiel") {}
object MusicBox extends Instrument(11, "Music Box") {}
object Vibraphone extends Instrument(12, "Vibraphone") {}
object Marimba extends Instrument(13, "Marimba") {}
object Xylophone extends Instrument(14, "Xylophone") {}
object TubularBells extends Instrument(15, "Tubular Bells") {}
object Dulcimer extends Instrument(16, "Dulcimer") {}

// Organ:
object DrawbarOrgan extends Instrument(17, "Drawbar Organ") {}
object PercussiveOrgan extends Instrument(18, "Percussive Organ") {}
object RockOrgan extends Instrument(19, "Rock Organ") {}
object ChurchOrgan extends Instrument(20, "Church Organ") {}
object ReedOrgan extends Instrument(21, "Reed Organ") {}
object Accordion extends Instrument(22, "Accordion") {}
object Harmonica extends Instrument(23, "Harmonica") {}
object TangoAccordion extends Instrument(24, "Tango Accordion") {}

// Guitar:
object AcousticGuitarNylon extends Instrument(25, "Acoustic Guitar (nylon)") {}
object AcousticGuitarSteel extends Instrument(26, "Acoustic Guitar (steel)") {}
object ElectricGuitarJazz extends Instrument(27, "Electric Guitar (jazz)") {}
object ElectricGuitarClean extends Instrument(28, "Electric Guitar (clean)") {}
object ElectricGuitarMuted extends Instrument(29, "Electric Guitar (muted)") {}
object OverdrivenGuitar extends Instrument(30, "Overdriven Guitar") {}
object DistortionGuitar extends Instrument(31, "Distortion Guitar") {}
object Guitarharmonics extends Instrument(32, "Guitar harmonics") {}

// Bass:
object AcousticBass extends Instrument(33, "Acoustic Bass") {}
object ElectricBassFinger extends Instrument(34, "Electric Bass (finger)") {}
object ElectricBassPick extends Instrument(35, "Electric Bass (pick)") {}
object FretlessBass extends Instrument(36, "Fretless Bass") {}
object SlapBass1 extends Instrument(37, "Slap Bass 1") {}
object SlapBass2 extends Instrument(38, "Slap Bass 2") {}
object SynthBass1 extends Instrument(39, "Synth Bass 1") {}
object SynthBass2 extends Instrument(40, "Synth Bass 2") {}

// Strings:
object Violin extends Instrument(41, "Violin") {}
object Viola extends Instrument(42, "Viola") {}
object Cello extends Instrument(43, "Cello") {}
object Contrabass extends Instrument(44, "Contrabass") {}
object TremoloStrings extends Instrument(45, "Tremolo Strings") {}
object PizzicatoStrings extends Instrument(46, "Pizzicato Strings") {}
object OrchestralHarp extends Instrument(47, "Orchestral Harp") {}
object Timpani extends Instrument(48, "Timpani") {}

// Strings (continued):
object StringEnsemble1 extends Instrument(49, "String Ensemble 1") {}
object StringEnsemble2 extends Instrument(50, "String Ensemble 2") {}
object SynthStrings1 extends Instrument(51, "Synth Strings 1") {}
object SynthStrings2 extends Instrument(52, "Synth Strings 2") {}
object ChoirAahs extends Instrument(53, "Choir Aahs") {}
object VoiceOohs extends Instrument(54, "Voice Oohs") {}
object SynthVoice extends Instrument(55, "Synth Voice") {}
object OrchestraHit extends Instrument(56, "Orchestra Hit") {}

// Brass:
object Trumpet extends Instrument(57, "Trumpet") {}
object Trombone extends Instrument(58, "Trombone") {}
object Tuba extends Instrument(59, "Tuba") {}
object MutedTrumpet extends Instrument(60, "Muted Trumpet") {}
object FrenchHorn extends Instrument(61, "French Horn") {}
object BrassSection extends Instrument(62, "Brass Section") {}
object SynthBrass1 extends Instrument(63, "Synth Brass 1") {}
object SynthBrass2 extends Instrument(64, "Synth Brass 2") {}

// Reed:
object SopranoSax extends Instrument(65, "Soprano Sax") {}
object AltoSax extends Instrument(66, "Alto Sax") {}
object TenorSax extends Instrument(67, "Tenor Sax") {}
object BaritoneSax extends Instrument(68, "Baritone Sax") {}
object Oboe extends Instrument(69, "Oboe") {}
object EnglishHorn extends Instrument(70, "English Horn") {}
object Bassoon extends Instrument(71, "Bassoon") {}
object Clarinet extends Instrument(72, "Clarinet") {}

// Pipe:
object Piccolo extends Instrument(73, "Piccolo") {}
object Flute extends Instrument(74, "Flute") {}
object Recorder extends Instrument(75, "Recorder") {}
object PanFlute extends Instrument(76, "Pan Flute") {}
object BlownBottle extends Instrument(77, "Blown Bottle") {}
object Shakuhachi extends Instrument(78, "Shakuhachi") {}
object Whistle extends Instrument(79, "Whistle") {}
object Ocarina extends Instrument(80, "Ocarina") {}

// Synth Lead:
object Lead1Square extends Instrument(81, "Lead 1 (square)") {}
object Lead2Sawtooth extends Instrument(82, "Lead 2 (sawtooth)") {}
object Lead3Calliope extends Instrument(83, "Lead 3 (calliope)") {}
object Lead4Chiff extends Instrument(84, "Lead 4 (chiff)") {}
object Lead5Charang extends Instrument(85, "Lead 5 (charang)") {}
object Lead6Voice extends Instrument(86, "Lead 6 (voice)") {}
object Lead7Fifths extends Instrument(87, "Lead 7 (fifths)") {}
object Lead8BassLead extends Instrument(88, "Lead 8 (bass + lead)") {}

// Synth Pad:
object Pad1NewAge extends Instrument(89, "Pad 1 (new age)") {}
object Pad2Warm extends Instrument(90, "Pad 2 (warm)") {}
object Pad3Polysynth extends Instrument(91, "Pad 3 (polysynth)") {}
object Pad4Choir extends Instrument(92, "Pad 4 (choir)") {}
object Pad5Bowed extends Instrument(93, "Pad 5 (bowed)") {}
object Pad6Metallic extends Instrument(94, "Pad 6 (metallic)") {}
object Pad7Halo extends Instrument(95, "Pad 7 (halo)") {}
object Pad8Sweep extends Instrument(96, "Pad 8 (sweep)") {}

// Synth Effects:
object FX1Rain extends Instrument(97, "FX 1 (rain)") {}
object FX2Soundtrack extends Instrument(98, "FX 2 (soundtrack)") {}
object FX3Crystal extends Instrument(99, "FX 3 (crystal)") {}
object FX4Atmosphere extends Instrument(100, "FX 4 (atmosphere)") {}
object FX5Brightness extends Instrument(101, "FX 5 (brightness)") {}
object FX6Goblins extends Instrument(102, "FX 6 (goblins)") {}
object FX7Echoes extends Instrument(103, "FX 7 (echoes)") {}
object FX8Scifi extends Instrument(104, "FX 8 (sci-fi)") {}

// Ethnic:
object Sitar extends Instrument(105, "Sitar") {}
object Banjo extends Instrument(106, "Banjo") {}
object Shamisen extends Instrument(107, "Shamisen") {}
object Koto extends Instrument(108, "Koto") {}
object Kalimba extends Instrument(109, "Kalimba") {}
object Bagpipe extends Instrument(110, "Bag pipe") {}
object Fiddle extends Instrument(111, "Fiddle") {}
object Shanai extends Instrument(112, "Shanai") {}

// Percussive:
object TinkleBell extends Instrument(113, "Tinkle Bell") {}
object Agogo extends Instrument(114, "Agogo") {}
object SteelDrums extends Instrument(115, "Steel Drums") {}
object Woodblock extends Instrument(116, "Woodblock") {}
object TaikoDrum extends Instrument(117, "Taiko Drum") {}
object MelodicTom extends Instrument(118, "Melodic Tom") {}
object SynthDrum extends Instrument(119, "Synth Drum") {}

// Sound effects:
object ReverseCymbal extends Instrument(120, "Reverse Cymbal") {}
object GuitarFretNoise extends Instrument(121, "Guitar Fret Noise") {}
object BreathNoise extends Instrument(122, "Breath Noise") {}
object Seashore extends Instrument(123, "Seashore") {}
object BirdTweet extends Instrument(124, "Bird Tweet") {}
object TelephoneRing extends Instrument(125, "Telephone Ring") {}
object Helicopter extends Instrument(126, "Helicopter") {}
object Applause extends Instrument(127, "Applause") {}
object Gunshot extends Instrument(128, "Gunshot") {}
}
