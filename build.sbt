
maintainer := "wa9nnn@u505.com"

name := "cabrillo"

organization := "com.github.dicklieber"


enablePlugins(JavaAppPackaging, JDKPackagerPlugin)

scalaVersion := "2.13.1"

//resolvers += Resolver.githubPackagesRepo("dicklieber", "cabrillo")

//publishTo := Some("GitHub Package Registry" at "https://maven.pkg.github.com/dicklieber/cabrillio")
//credentials += Credentials("GitHub Package Registry","maven.pkg.github.com","dicklieber","019017b5b5e121cce8810c3aa8b083c887625591")

//resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

//resolvers += Resolver.bintrayRepo("dicklieber", "maven")
//resolvers += Resolver.bintrayIvyRepo("dicklieber", "maven")

resolvers += "Nexus" at "https://oss.sonatype.org/content/repositories/snapshots"

//credentials += Credentials("Sonatype Nexus Repository Manager", "https://oss.sonatype.org", "dicklieber", "Nagqu3-pytmon-qidzoq")


//publishTo := {
//  val nexus = "https://oss.sonatype.org/"
//  if (isSnapshot.value)
//    Some("snapshots" at nexus + "content/repositories/snapshots")
//  else
//    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
//}
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

credentials += Credentials(Path.userHome / ".sbt" / "sonatype_credential")

ThisBuild / organization := "com.github.dicklieber"
ThisBuild / organizationName := "Dick Lieber WA9NNN"
ThisBuild / organizationHomepage := Some(url("http://www.u505.com/cabrillo"))

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/dicklieber/cabrillio"),
    "scm:git@github.com:dickieber/cabrillo.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id    = "wa9nnn",
    name  = "Dick Lieber",
    email = "your@email",
    url   = url("http://www.u505.com/cabrillo")
  )
)

ThisBuild / description := "Scala library to process amateur radio contest cabrillo format files."
ThisBuild / licenses := List("GPLv3" -> new URL("https://www.gnu.org/licenses/quick-guide-gplv3.html"))
ThisBuild / homepage := Some(url("https://github.com/dicklieber/cabrillio"))

// Remove all additional repository other than Maven Central from POM
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
ThisBuild / publishMavenStyle := true

publishTo := sonatypePublishToBundle.value

