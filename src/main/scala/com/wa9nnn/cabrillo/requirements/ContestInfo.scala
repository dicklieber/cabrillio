
package com.wa9nnn.cabrillo.requirements

import com.wa9nnn.cabrillo.model.{Exchange, Qso}
import com.wa9nnn.cabrillo.parsers.LineBody
import com.wa9nnn.cabrillo.model.{Exchange, Qso}
import com.wa9nnn.cabrillo.parsers.LineBody

trait ContestInfo {
  /**
   *
   * @param location of interest.
   * @throws Exception on error
   */
  def validateLocation(location: String): Unit

  def validateCatagory(location: String): Unit

  def validateExchange(exchange: Exchange): Unit

  def parseQso(lineBody:LineBody) : Qso
}

