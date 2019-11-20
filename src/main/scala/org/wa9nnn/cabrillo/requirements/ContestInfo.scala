
package org.wa9nnn.cabrillo.requirements

import org.wa9nnn.cabrillo.model.{Exchange, Qso, TagValue}
import org.wa9nnn.cabrillo.parsers.LineBody

trait ContestInfo {
  /**
   *
   * @param location
   * @throws Exception
   */
  def validateLocation(location: String): Unit

  def validateCatagory(location: String): Unit

  def validateExchange(exchange: Exchange): Unit

  def parseQso(lineBody:LineBody) : Qso
}

