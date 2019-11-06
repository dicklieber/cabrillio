
package org.wa9nnn.cabrillo.requirements

import com.typesafe.scalalogging.LazyLogging
import org.wa9nnn.cabrillo.model.CabrilloTypes.Tag
import org.wa9nnn.cabrillo.model.{ParsedCabrillo, TagValue}

object Contest extends Checker with LazyLogging {
  override def tag: Tag = "CONTEST"


  override def check(parsedCabrillo: ParsedCabrillo): Seq[CabrilloError] = {
    logger.debug(s"checking $tag")
    val tagValues: Seq[TagValue] = parsedCabrillo(tag)
    tagValues.size match {
      case 0 ⇒
        missingTag
      case 1 ⇒
        val head = tagValues.head
        val expectedContest = "WFD"
        if (head.body == expectedContest) {
          ok
        } else {
          failure(head, s"""$tag is not "$expectedContest"""")
        }
      case _ ⇒
        val lineNumbers = tagValues.map {
          _.lineNumber
        }
        tooMany(lineNumbers)

    }
  }
}
