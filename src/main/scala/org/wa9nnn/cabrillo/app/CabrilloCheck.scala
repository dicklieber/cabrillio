
package org.wa9nnn.cabrillo.app

import java.io.{File, PrintWriter}
import java.nio.file.Files

import org.wa9nnn.cabrillo.reporters.ReportJson
import org.wa9nnn.cabrillo.{Cabrillo, Result}
import scopt.{OParser, OParserBuilder}

import scala.io.Source
import scala.util.Using

object CabrilloCheck {

  case class Config(
                     foo: Int = -1,

                     in: File = new File("."),
                     out: File = new File("."),
                     syntax: String = "json",
                   )

  val builder: OParserBuilder[Config] = OParser.builder[Config]
  val parser1: OParser[Unit, Config] = {
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
        val cabrilloFile: File = config.in
       val bytes: Array[Byte] =  Files.readAllBytes(cabrilloFile.toPath)
//        val source = Source.fromFile(cabrilloFile, "ASCII").getLines().toSeq
        val result: Result = Cabrillo(bytes).result
        Using(new PrintWriter(System.out)) { writer ⇒
          ReportJson.generate(result, writer)
        }
      case _ =>
        println("bad cli")
      // arguments are bad, error message will have been displayed
    }

  }
}
