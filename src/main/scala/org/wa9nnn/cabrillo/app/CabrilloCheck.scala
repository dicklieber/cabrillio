
package org.wa9nnn.cabrillo.app

import java.io.{PrintWriter, StringWriter}
import java.net.URL
import java.nio.file.Paths

import org.wa9nnn.cabrillo.report.ReportJson
import org.wa9nnn.cabrillo.{Cabrillo, Result}

import scala.io.Source
import scala.util.Using

object CabrilloCheck {
  def main(args: Array[String]): Unit = {
    val cabrilloFile = args(0)
    val path = Paths.get(cabrilloFile)
    val source = Source.fromFile(path.toFile, "ASCII")
    val url: URL = new URL("file", "", path.toString)
    val result: Result = Cabrillo(source, url)
    Using(new PrintWriter(System.out)) { writer ⇒
      ReportJson.generate(result, writer)
    }

  }
}
