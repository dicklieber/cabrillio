
maintainer := "wa9nnn@u505.com"

name := "cabrillo"

organization := "org.wa9nnn"


enablePlugins(JavaAppPackaging, JDKPackagerPlugin)

scalaVersion := "2.13.1"

//resolvers += Resolver.githubPackagesRepo("dicklieber", "cabrillo")

//publishTo := Some("GitHub Package Registry" at "https://maven.pkg.github.com/dicklieber/cabrillio")
//credentials += Credentials("GitHub Package Registry","maven.pkg.github.com","dicklieber","019017b5b5e121cce8810c3aa8b083c887625591")

//resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

//resolvers += Resolver.bintrayRepo("dicklieber", "maven")
//resolvers += Resolver.bintrayIvyRepo("dicklieber", "maven")



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

//ThisBuild / version := "<YOUR PLUGIN VERSION HERE>"
//ThisBuild / organization := "<INSERT YOUR ORG HERE>"
//ThisBuild / description := "<YOUR DESCRIPTION HERE>"

// This is an example.  sbt-bintray requires licenses to be specified
// (using a canonical name).
//ThisBuild / licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))


////bintrayReleaseOnPublish in ThisBuild := true
//publishMavenStyle := true
//licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

//todo use https://github.com/sbt/sbt-release
//todo publish jar to some public repo.