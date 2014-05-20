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
    ,"log4j" % "log4j" % "1.2.17"
   //,"com.locurasoft" % "osxmidi4j" % "1.0" 
)

unmanagedJars in Compile ++= {
    val base = baseDirectory.value
    val baseDirectories = file("/System/Library/Java/Extensions") +++ file("/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/jre/lib")
    val customJars = (baseDirectories ** "*.jar")
    customJars.classpath
}