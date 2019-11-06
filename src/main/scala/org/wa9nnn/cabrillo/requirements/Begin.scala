
package org.wa9nnn.cabrillo.requirements

import com.typesafe.scalalogging.LazyLogging
import org.wa9nnn.cabrillo.model.CabrilloTypes.Tag
import org.wa9nnn.cabrillo.model.{ParsedCabrillo, TagValue}

object Begin extends Checker with LazyLogging {
  override def tag: Tag = "START-OF-LOG"


  override def check(parsedCabrillo: ParsedCabrillo): Seq[CabrilloError] = {
    logger.debug(s"checking $tag")
    val tagValues: Seq[TagValue] = parsedCabrillo(tag)
    tagValues.size match {
      case 0 ⇒
        missingTag
      case 1 ⇒
        val head = tagValues.head
        if (head.body == "3.0") {
          ok
        } else {
          failure(head, s"""$tag is not "V3.0"""")
        }
      case _ ⇒
        val lineNumbers = tagValues.map {
          _.lineNumber
        }
        tooMany(lineNumbers)

    }
  }
}
