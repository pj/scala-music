package nz.kiwi.johnson.scalam

sealed trait Length 

object Whole extends Length
object Half extends Length
object Quaver extends Length
object Eighth extends Length
object SemiQuaver extends Length