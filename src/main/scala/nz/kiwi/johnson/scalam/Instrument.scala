package nz.kiwi.johnson.scalam

class Instrument(instrumentNumber: Int, name: String) {
  
}

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
