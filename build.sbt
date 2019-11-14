name := "Cabrillo"

version := "0.1"

scalaVersion := "2.13.1"

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"


scalacOptions in(Compile, doc) ++= Seq("-verbose", "-Yliteral-types")

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "4.6.0" % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "nl.grons" %% "metrics4-scala" % "4.1.1",
)
