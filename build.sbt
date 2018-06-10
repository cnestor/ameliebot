name := "Discord"

version := "1.0"

scalaVersion := "2.12.4"

resolvers += "jcenter" at "http://jcenter.bintray.com" //for discord4j

libraryDependencies += "com.discord4j" % "Discord4J" % "2.10.1"
libraryDependencies += "com.typesafe" % "config" % "1.3.2"