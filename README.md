# Scala Music

Some ideas for a scala music dsl based on trackers like fast tracker and impulse tracker.

Example:

```scala
object music {
	// Create passages
	val p44 = passage(
	          adagio, 
	          4-/-4,
			  inst || AcousticGrandPiano ||,
			  q    -- A4 quaver          ||,
			  q    || C4 quaver          ||,
			  q    || E4 quaver          ||,
			  q    || A4 quaver          ||
			  )
	
	val p34 = passage(
	          largo,
			  3-/-4,
			  inst || Celesta            ||,
			  q    -- A4 quaver          ||,
			  q    || C4 quaver          ||,
			  q    || E4 quaver          ||
	          )
	          
	 // Sequence passages
	 val sequence = p44 | p34
	 
	 // generate midisequence for playback.         
	 val midiSequence = MidiUtils.sequenceToMidiSequence(sequence)
}
```

TODO:

- Line suffixes for velocity and pitch
- Fully test all possibilities for notation
- Investigate reactive programming for combining Midi instruments
- Wrappers around underlying Midi library
- Playback thread
- Drum notation support
- Notation for control channels
- Main script that generates list of devices and code to insert into project
- Add support for key to make printing notes easier