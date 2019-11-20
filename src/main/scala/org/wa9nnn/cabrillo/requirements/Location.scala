
package org.wa9nnn.cabrillo.requirements

import org.wa9nnn.cabrillo.model.Cabrillo

case class Location()(implicit ontestInfo: ContestInfo) extends TagHandler("LOCATION") {

  override def tagCheck(parsedCabrillo: Cabrillo)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {

    val head = parsedCabrillo(tag).head
    try {
      ontestInfo.validateLocation(head.body)
      Seq.empty
    } catch {
      case e: Exception ⇒
        Seq(new ValueErrorAtLine(head, e.getMessage))
      case _ : Throwable⇒
        Seq.empty
    }
  }
}
case class Section()(implicit ontestInfo: ContestInfo) extends TagHandler("ARRL-SECTION") {

  override def tagCheck(parsedCabrillo: Cabrillo)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {

    val head = parsedCabrillo(tag).head
    try {
      ontestInfo.validateLocation(head.body)
      Seq.empty
    } catch {
      case e: Exception ⇒
        Seq(new ValueErrorAtLine(head, e.getMessage))
      case _: Throwable ⇒
        Seq.empty
    }
  }
}
case class Category()(implicit ontestInfo: ContestInfo) extends TagHandler("CATEGORY") {

  override def tagCheck(parsedCabrillo: Cabrillo)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {

    val head = parsedCabrillo(tag).head
    try {
      ontestInfo.validateCatagory(head.body)
      Seq.empty
    } catch {
      case e: Exception ⇒
        Seq(new ValueErrorAtLine(head, e.getMessage))
      case _ : Throwable⇒
        Seq.empty
    }
  }
}
