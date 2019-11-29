
package org.wa9nnn.cabrillo.app

import java.io.ObjectInputFilter.Config
import java.io.{File, PrintWriter, StringWriter}
import java.net.URL
import java.nio.file.Paths

import org.wa9nnn.cabrillo.report.ReportJson
import org.wa9nnn.cabrillo.{Cabrillo, Result}

import scala.io.Source
import scala.util.Using
import scopt.OParser

object CabrilloCheck {

  case class Config(
                     foo: Int = -1,

                     in: File = new File("."),
                     out: File = new File("."),
                     syntax: String = "json",
//                     xyz: Boolean = false,
//                     libName: String = "",
//                     maxCount: Int = -1,
//                     verbose: Boolean = false,
//                     debug: Boolean = false,
//                     mode: String = "",
//                     files: Seq[File] = Seq(),
//                     keepalive: Boolean = false,
//                     jars: Seq[File] = Seq(),
//                     kwargs: Map[String, String] = Map()
                   )

  val builder = OParser.builder[Config]
  val parser1 = {
    import builder._
    OParser.sequence(
      programName("cabrillo check"),
      head("cabrilloCheck", "0.1"),
      // option -f, --foo
      opt[File]('i', "in")
        .action((x, c) =>
          c.copy(in = x))
        .text("cabrillo file to processs.") ,
      opt[File]('i', "out")
        .action((x, c) =>
          c.copy(out = x))
        .text("cabrillo file to processs."),
      // more options here...
    )
  }


  def main(args: Array[String]): Unit = {
    println("Hello cabrillo")
    OParser.parse(parser1, args, Config()) match {
      case Some(config) =>
        val cabrilloFile = config.in
        val source = Source.fromFile(cabrilloFile, "ASCII")
        val url: URL = new URL("file", "", cabrilloFile.toString)
        val result: Result = Cabrillo(source, url)
        Using(new PrintWriter(System.out)) { writer ⇒
          ReportJson.generate(result, writer)
        }
      case _ =>
        println("bad cli")
      // arguments are bad, error message will have been displayed
    }

//    val cabrilloFile = args(0)
//    val path = Paths.get(cabrilloFile)
//    val source = Source.fromFile(path.toFile, "ASCII")
//    val url: URL = new URL("file", "", path.toString)
//    val result: Result = Cabrillo(source, url)
//    Using(new PrintWriter(System.out)) { writer ⇒
//      ReportJson.generate(result, writer)
//    }

  }
}
