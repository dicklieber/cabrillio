
package org.wa9nnn.cabrillo.contests

import org.wa9nnn.cabrillo.model.{Exchange, Qso}
import org.wa9nnn.cabrillo.parsers.{LineBody, QsoTagParser_WFD}
import org.wa9nnn.cabrillo.requirements.ContestInfo

class ContestInfoWFD extends ContestInfo {
  //  private val Parse = """(\d*\p{Upper})""".r
  private val qsoTagParser_WFD = new QsoTagParser_WFD
  private val arrlSections = new ArrlSections

  /**
   *
   * @param location code e.g. "IL"
   * @throws Exception
   */
  override def validateLocation(location: String): Unit = {
    arrlSections.check(location)
  }

  override def validateCatagory(category: String): Unit = {

  }

  override def validateExchange(exchange: Exchange): Unit = {
    throw new NotImplementedError()
  }

  def parseQso(lineBody: LineBody): Qso = {
    qsoTagParser_WFD.parse(lineBody)
  }
}