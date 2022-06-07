name := "cabrillo-lib"


//enablePlugins(JavaAppPackaging, JDKPackagerPlugin)

scalaVersion := "2.13.1"

//javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")
//initialize := {
//  val _ = initialize.value
//  val javaVersion = sys.props("java.specification.version")
////  if (javaVersion != "1.8")
////    sys.error("Java 1.8 is required for this project. Found " + javaVersion + " instead")
//}

//resolvers += Resolver.bintrayRepo("dicklieber", "maven-cabrillo")

  scalacOptions in(Compile, doc) ++= Seq("-verbose")
//scalacOptions in(Compile, doc) ++= Seq("-verbose", "-Yliteral-types")

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "4.6.0" % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "nl.grons" %% "metrics4-scala" % "4.1.1",
  "com.typesafe.play" %% "play-json" % "2.8.0",
  "com.github.scopt" %% "scopt" % "4.0.0-RC2",
  "commons-io" % "commons-io" % "2.6"
)

ThisBuild / organization := "com.wa9nnn"
ThisBuild / organizationName := "Dick Lieber WA9NNN"
ThisBuild / organizationHomepage := Some(url("http://www.u505.com/cabrillo"))
ThisBuild / licenses := List("GPL-3.0" -> new URL("https://www.gnu.org/licenses/quick-guide-gplv3.html"))


//resolvers += ("reposilite-repository-releases" at  ("http://194.113.64.105:8080/releases")).withAllowInsecureProtocol(true)

credentials += Credentials(Path.userHome / ".sbt" / "credentials-reposolite")

publishTo := Some(("Reposilite" at "http://194.113.64.105:8080/releases").withAllowInsecureProtocol(true))



