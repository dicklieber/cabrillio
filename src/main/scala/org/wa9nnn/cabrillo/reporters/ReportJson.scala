
package org.wa9nnn.cabrillo.reporters


import java.io.Writer
import java.net.URL

import org.wa9nnn.cabrillo.Result
import org.wa9nnn.cabrillo.requirements.CabrilloError
import play.api.libs.json.{Format, JsError, JsResult, JsString, JsSuccess, JsValue, Json}

object ReportJson {
  private implicit val urlformat = org.wa9nnn.cabrillo.reporters.UrlFormat.urlFormat
  implicit val errorAtLineFormat: Format[CabrilloError] = Json.format[CabrilloError]
  implicit val resultFormat: Format[Result] = Json.format[Result]


  def generate(result: Result, writer: Writer): Unit = {
    writer.write(Json.prettyPrint(Json.toJson(result)))
  }
}

object UrlFormat {
  implicit val  urlFormat: Format[URL] = new Format[URL] {
    override def reads(json: JsValue): JsResult[URL] = {
      val ss = json.as[String]

      try {
        JsSuccess(new URL(ss))
      }
      catch {
        case e: IllegalArgumentException ⇒ JsError(e.getMessage)
      }
    }

    override def writes(url: URL): JsValue = {
      JsString(url.toExternalForm)
    }
  }

}
