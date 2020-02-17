
package com.wa9nnn.cabrillo.requirements

import com.wa9nnn.cabrillo.model.{CabrilloData, TagValue}
import com.wa9nnn.cabrillo.model.CabrilloData

case class Location()(implicit contestInfo: ContestInfo) extends TagHandler("LOCATION") {

  override def tagCheck(parsedCabrillo: CabrilloData)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {

    val head: TagValue = parsedCabrillo(tag).head
    try {
      contestInfo.validateLocation(head.body)
      Seq.empty
    } catch {
      case e: Exception ⇒
        Seq(CabrilloError(head, e.getMessage))
      case _: Throwable ⇒
        Seq.empty
    }
  }
}

case class Section()(implicit ontestInfo: ContestInfo) extends TagHandler("ARRL-SECTION") {

  override def tagCheck(parsedCabrillo: CabrilloData)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {

    val head = parsedCabrillo(tag).head
    try {
      ontestInfo.validateLocation(head.body)
      Seq.empty
    } catch {
      case e: Exception ⇒
        Seq(CabrilloError(head, e.getMessage))
      case _: Throwable ⇒
        Seq.empty
    }
  }
}

case class Category()(implicit contestInfo: ContestInfo) extends TagHandler("CATEGORY") {

  override def tagCheck(parsedCabrillo: CabrilloData)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {

    val head = parsedCabrillo(tag).head
    try {
      contestInfo.validateCatagory(head.body)
      Seq.empty
    } catch {
      case e: Exception ⇒
        Seq(CabrilloError(head, e.getMessage))
      case _: Throwable ⇒
        Seq.empty
    }
  }
}
