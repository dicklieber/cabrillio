
package org.wa9nnn.cabrillo.requirements

import org.wa9nnn.cabrillo.model.{ CabrilloDataProvider, TagValue}

object Contest extends TagHandler("CONTEST") {

  override def tagCheck(parsedCabrillo: CabrilloDataProvider)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    logger.debug(s"checking $tag")
    val tagValues: Seq[TagValue] = parsedCabrillo(tag)
    val head = tagValues.head
    val expectedContest = "WFD"
    if (head.body == expectedContest) {
      Seq.empty
    } else {
      failure(head, s"""$tag is not "V3.0"""")
    }
  }
}
