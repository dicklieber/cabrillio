import NativePackagerHelper._
maintainer := "wa9nnn@u505.com"

name := "Cabrillo"

version := "0.1"

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

// Example of specifying a fallback location of `ant-javafx.jar` if plugin can't find it.
//(antPackagerTasks in JDKPackager) := (antPackagerTasks in JDKPackager).value orElse {
//  for {
//    f <- Some(file("/usr/lib/jvm/java-8-oracle/lib/ant-javafx.jar")) if f.exists()
//  } yield f
//}
//
//mappings in Universal ++= {
//  val jresDir = Path.userHome / ".jre"
//  val jreLink = "JRE64_1.8"
//  val linux64Jre = jresDir.toPath.resolve("linux64")
//  directory(linux64Jre.toFile).map { j =>
//    j._1 -> j._2.replace(jreLink, "jre")
//  }
//}

//mappings in Universal ++= {
//  val jresDir = Path.userHome / $JAVA_HOME
////  val jresDir = Path.userHome / ".jre"
//  val linux64Jre = jresDir.toPath.resolve("linux64")
//  directory(linux64Jre.toFile).map { j =>
//    j._1 -> j._2.replace("jreLink", "jre")
//  }
//}
