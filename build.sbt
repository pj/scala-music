name := "scalam"

version := "1.0"

scalaVersion := "2.11.0"

resolvers ++= Seq("Couchbase Maven Repository" at "http://files.couchbase.com/maven2",
         "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
       "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
         "releases"  at "http://oss.sonatype.org/content/repositories/releases",
         "spray repo" at "http://repo.spray.io")
         
libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.1.4" % "test" withSources() withJavadoc()
)