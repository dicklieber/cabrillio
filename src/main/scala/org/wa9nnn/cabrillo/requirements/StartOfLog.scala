
package org.wa9nnn.cabrillo.requirements

import com.typesafe.scalalogging.LazyLogging
import org.wa9nnn.cabrillo.model.{Cabrillo, TagValue}

object StartOfLog extends TagHandler("START-OF-LOG") with LazyLogging {

  override def tagCheck(parsedCabrillo: Cabrillo)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {

    val tagValues: Seq[TagValue] = parsedCabrillo(tag)
    tagValues.headOption match {
      case Some(tv) ⇒
        if (tv.body == "3.0") {
          Seq.empty
        } else {
          failure(tv, s"""$tag is not "V3.0"""")
        }
      case None ⇒
        Seq(MissingTag(tag))
    }
  }
}

object EndOfLog extends TagHandler("END-OF-LOG") with LazyLogging {

  override def tagCheck(parsedCabrillo: Cabrillo)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    val tagValues: Seq[TagValue] = parsedCabrillo(tag)
    val head = tagValues.head
    if (head.lineNumber == parsedCabrillo.lineCount) {
      Seq.empty
    } else {
      failure(head, s"""$tag is not last line in file!""")
    }
  }
}
