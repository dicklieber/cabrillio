
package org.wa9nnn.cabrillo.report

import java.net.URL

import play.api.libs.json._

object UrlFormt {
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
