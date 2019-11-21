
package org.wa9nnn.cabrillo.report


import java.io.Writer

import org.wa9nnn.cabrillo.Result
import org.wa9nnn.cabrillo.requirements.CabrilloError
import play.api.libs.json.{Format, Json}

object ReportJson {
  import org.wa9nnn.cabrillo.report.UrlFormt.urlFormat
  implicit val errorAtLineFormat: Format[CabrilloError] = Json.format[CabrilloError]
  implicit val resultFormat: Format[Result] = Json.format[Result]


  def generate(result: Result, writer:Writer) :Unit = {
    writer.write( Json.prettyPrint( Json.toJson(result)))
  }


}
