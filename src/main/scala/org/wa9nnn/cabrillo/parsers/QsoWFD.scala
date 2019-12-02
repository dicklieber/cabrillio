
package org.wa9nnn.cabrillo.parsers

import java.time.ZonedDateTime

import org.wa9nnn.cabrillo.model.CabrilloTypes.Tags
import org.wa9nnn.cabrillo.model._
import org.wa9nnn.cabrillo.requirements.{AnyNumber, CabrilloError, ContestInfo, TagHandler}

class QsoTagHandler_WFD extends TagHandler("QSO", AnyNumber) {
  override val tag: String = QsoTagHandler_WFD.tag
  private val mainp = """(\S+)\s+(\S+)\s+([\d\-]+\s+\d+)\s+(\S+\s+\S+\s+\S+)\s++(\S+\s+\S+\s+\S+)""".r()

  override def parse(lineBody: LineBody): Qso = {
    val body = lineBody.body
    try {
      val mainp(sFreq, sMode, sDateTime, sSent, sReceived) = body
      val lineNumber = lineBody.lineNumber
      val zdt: ZonedDateTime = DateTimeParser(lineNumber, sDateTime)
      QSO_WFD(tag, lineNumber, body, sFreq, sMode, zdt, toInfo(sSent), toInfo(sReceived))
    } catch {
      case x: Exception ⇒
        QSO_NoParse(tag, lineBody.lineNumber, body)
    }
  }

  private def toInfo(sInfo: String): Exchange_WFD = {
    val infop = "(\\S+)\\s+(\\S+)\\s+(\\S+)".r
    val infop(callsign, category, section) = sInfo
    Exchange_WFD(callsign, category, section)
  }


  override def tagCheck(cabrilloData: CabrilloDataProvider)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    val tags: Tags = cabrilloData.apply(tag)
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

case class Exchange_WFD(callsign: String, category: String, section: String) extends Exchange

case object ExchangeNoParse extends Exchange

case class QSO_WFD(tag: String, lineNumber: Int, body: String, freq: String, mode: String, stamp: ZonedDateTime, sent: Exchange_WFD, received: Exchange_WFD) extends Qso {
  def check()(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    checkExchange(sent) ++ checkExchange(received) ++
      checkFreq ++
      checkMode

  }

  def checkMode: Seq[CabrilloError] = {
    if(QsoTagHandler_WFD.modes.contains(mode.toLowerCase)){
      Seq.empty
    }else{
      Seq(CabrilloError(this, s"""Mode CW,DI or PH but: "$mode""""))
    }
  }

  def checkStamp: Seq[CabrilloError] = {
    Seq.empty
  }


  def checkExchange(exchange: Exchange_WFD): Seq[CabrilloError] = {
    Seq.empty
  }


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
}