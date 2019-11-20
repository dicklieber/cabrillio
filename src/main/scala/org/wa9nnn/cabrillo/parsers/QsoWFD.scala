
package org.wa9nnn.cabrillo.parsers

import java.time.ZonedDateTime

import org.wa9nnn.cabrillo.model.CabrilloTypes.Tags
import org.wa9nnn.cabrillo.model._
import org.wa9nnn.cabrillo.requirements.{Any, CabrilloError, ContestInfo, TagHandler, ValueErrorAtLine}

class QsoTagParser_WFD extends TagHandler("QSO", Any) {
  override val tag: String = QsoTagParser_WFD.tag
  private val mainp = """(\S+)\s+(\S+)\s+([\d\-]+\s+\d+)\s+(\S+\s+\S+\s+\S+)\s++(\S+\s+\S+\s+\S+)""".r()

  override def parse(lineBody: LineBody): QSO_WFD = {
    val body = lineBody.body
    val mainp(sFreq, sMode, sDateTime, sSent, sReceived) = body
    val lineNumber = lineBody.lineNumber
    val zdt: ZonedDateTime = DateTimeParser(lineNumber, sDateTime)
    QSO_WFD(tag, lineNumber, body, sFreq, sMode, zdt, toInfo(sSent), toInfo(sReceived))
  }

  private def toInfo(sInfo: String): Exchange_WFD = {
    val infop = "(\\S+)\\s+(\\S+)\\s+(\\S+)".r
    val infop(callsign, category, section) = sInfo
    Exchange_WFD(callsign, category, section)
  }


  override def tagCheck(parsedCabrillo: Cabrillo)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    val tags: Tags = parsedCabrillo.apply(tag)
    tags.flatMap { tv: TagValue ⇒
      tv.asInstanceOf[QSO_WFD].check
    }

  }
}

object QsoTagParser_WFD {
  val tag: String = "QSO"
}

case class Exchange_WFD(callsign: String, category: String, section: String) extends Exchange

case class QSO_WFD(tag: String, lineNumber: Int, body: String, freq: String, mode: String, stamp: ZonedDateTime, sent: Exchange_WFD, received: Exchange_WFD) extends Qso {
  def check()(implicit contestInfo: ContestInfo): Seq[ValueErrorAtLine] = {
    checkExchange(sent) ++ checkExchange(received) ++
      checkFreq ++
      checkMode

  }

  def checkMode: Seq[ValueErrorAtLine] = {
    Seq.empty
  }
 def checkStamp: Seq[ValueErrorAtLine] = {
    Seq.empty
  }


  def checkExchange(exchange: Exchange_WFD): Seq[ValueErrorAtLine] = {
    Seq.empty
  }


}