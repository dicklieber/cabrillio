import NativePackagerHelper._
maintainer := "wa9nnn@u505.com"

name := "cabrillo"

organization := "org.wa9nnn"

version := "0.2-SNAPSHOT"

enablePlugins(JavaAppPackaging, JDKPackagerPlugin)

scalaVersion := "2.13.1"

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

scalacOptions in(Compile, doc) ++= Seq("-verbose")
//scalacOptions in(Compile, doc) ++= Seq("-verbose", "-Yliteral-types")

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "4.6.0" % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "nl.grons" %% "metrics4-scala" % "4.1.1",
  "com.typesafe.play" %% "play-json" % "2.8.0",
  "com.github.scopt" %% "scopt" % "4.0.0-RC2"
)
