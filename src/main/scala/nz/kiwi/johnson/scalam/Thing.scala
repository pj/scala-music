package nz.kiwi.johnson.scalam

object foo {
  override def toString = "foo"
}

object bar {
  override def toString = "bar"
}

object Test {
  
  def main(args: Array[String]) {	  
	  val x = foo
	  
	  x match {
	    case bar => {
	      println("Found bar")
	    }
	    case x => {
	      println("Not bar: " + x.toString)
	    }
	  }
  }
}