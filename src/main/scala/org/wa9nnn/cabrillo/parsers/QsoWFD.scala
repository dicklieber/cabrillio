
package org.wa9nnn.cabrillo.parsers

import java.time.{LocalDateTime, ZoneId, ZoneOffset, ZonedDateTime}

import org.wa9nnn.cabrillo.model.CabrilloTypes.{Tag, TagValues}
import org.wa9nnn.cabrillo.model._
import org.wa9nnn.cabrillo.requirements.{AnyNumber, CabrilloError, ContestInfo, TagHandler}

class QsoTagHandler_WFD extends TagHandler("QSO", AnyNumber) {
  override val tag: String = QsoTagHandler_WFD.tag
  private val mainRegx = """(\S+)\s+(\S+)\s+([\d\-]+\s+\d+)\s+(\S+\s+\S+\s+\S+)\s++(\S+\s+\S+\s+\S+)""".r
  private val exhcangeRegx = "(\\S+)\\s+(\\S+)\\s+(\\S+)".r

  override def parse(lineBody: LineBody): Qso = {
    val body = lineBody.body
    try {
      val mainRegx(sFreq, sMode, sDateTime, sSent, sReceived) = body
      val lineNumber = lineBody.lineNumber
      val zdt: ZonedDateTime = DateTimeParser(lineNumber, sDateTime)
      QSO_WFD(lineNumber, body, sFreq, sMode, zdt, toExchange(sSent), toExchange(sReceived))
    } catch {
      case _: Exception ⇒
        QSO_NoParse(tag, lineBody.lineNumber, body)
    }
  }

  private def toExchange(sInfo: String): Exchange_WFD = {
    val exhcangeRegx(callsign, category, section) = sInfo
    Exchange_WFD(callsign, category, section)
  }


  override def tagCheck(cabrilloData: CabrilloData)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    val tags: TagValues = cabrilloData.apply(tag)
    tags.flatMap { tv: TagValue ⇒
      try {
        tv.check()
      } catch {
        case e: Exception ⇒
          Seq(CabrilloError(tv, e))
      }
    }
  }
}

object QsoTagHandler_WFD {
  val tag: String = "QSO"
  val modes = Set(
    "cw",
    "di",
    "ph"
  )
}

case class Exchange_WFD(callsign: String, category: String, section: String) extends Exchange {
  override def toString: Tag = s"$callsign $category $section"
}

case object ExchangeNoParse extends Exchange

/**
 * Normal result of parsing a QSO
 *
 * @param lineNumber where in file.
 * @param body       lValue
 * @param freq       freq value.
 * @param mode       mode string.
 * @param stamp      when QSO occurred.
 * @param sent       exchange ghat was sent.
 * @param received   exchangfe that was received.
 */
case class QSO_WFD(lineNumber: Int, body: String, freq: String, mode: String, stamp: ZonedDateTime, sent: Exchange_WFD, received: Exchange_WFD) extends Qso {
  def check()(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    checkExchange(sent) ++ checkExchange(received) ++
      checkFreq ++
      checkMode
  }

  def checkMode: Seq[CabrilloError] = {
    if (QsoTagHandler_WFD.modes.contains(mode.toLowerCase)) {
      Seq.empty
    } else {
      Seq(CabrilloError(this, s"""Mode CW,DI or PH but: "$mode""""))
    }
  }

  def checkStamp: Seq[CabrilloError] = {
    Seq.empty
  }


  def checkExchange(exchange: Exchange_WFD): Seq[CabrilloError] = {
    Seq.empty
  }
  override def withLineNumber(lineNumber: Int): TagValue = {
    copy(lineNumber=lineNumber)
  }

  override def tag: Tag = "QSO"
}

case class QSO_NoParse(tag: String, lineNumber: Int, body: String, stamp: ZonedDateTime = ZonedDateTime.now()) extends Qso {
  def check()(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    Seq(CabrilloError(lineNumber, body, tag, "Unparsable QSO"))
  }

  def checkMode: Seq[CabrilloError] = {
    Seq.empty
  }

  def checkStamp: Seq[CabrilloError] = {
    Seq.empty
  }


  def checkExchange(exchange: Exchange_WFD): Seq[CabrilloError] = {
    Seq.empty
  }

  override def freq: String = "?"

  override def mode: String = "?"

  override def sent: Exchange = ExchangeNoParse

  override def received: Exchange = ExchangeNoParse
  override def withLineNumber(lineNumber: Int): TagValue = {
    copy(lineNumber=lineNumber)
  }

}


