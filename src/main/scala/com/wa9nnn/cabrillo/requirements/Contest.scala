
package com.wa9nnn.cabrillo.requirements

import com.wa9nnn.cabrillo.model.{CabrilloData, TagValue}
import com.wa9nnn.cabrillo.model.CabrilloData

object Contest extends TagHandler("CONTEST") {

  override def tagCheck(parsedCabrillo: CabrilloData)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    logger.debug(s"checking $tag")
    val tagValues: Seq[TagValue] = parsedCabrillo(tag)
    val head = tagValues.head
    val expectedContest = "WFD"
     if (head.body.startsWith(expectedContest)) {
      Seq.empty
    } else {
      failure(head, s"""$tag is not "$expectedContest"""")
    }
  }
}
