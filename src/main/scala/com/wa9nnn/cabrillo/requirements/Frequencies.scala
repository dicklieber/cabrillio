
package com.wa9nnn.cabrillo.requirements

import com.typesafe.scalalogging.LazyLogging

/**
 * See http://wwrof.org/cabrillo/cabrillo-qso-templates/ "freq"
 */
object Frequencies extends LazyLogging {
  def check(freq: String): String = {
    logger.debug(s"Checking freq: $freq")
    freq match {
      case "160M" => freq
      case "80M" => freq
      case "40M" => freq
      case "20M" => freq
      case "15M" => freq
      case "10M" => freq
      case "6M" => freq
      case "4M" => freq
      case "2M" => freq
      case "50" => freq
      case "70" => freq
      case "144" => freq
      case "222" => freq
      case "432" => freq
      case "902" => freq
      case "1.2G" => freq
      case "2.3G" => freq
      case "3.4G" => freq
      case "5.7G" => freq
      case "10G" => freq
      case "24G" => freq
      case "47G" => freq
      case "75G" => freq
      case "123G" => freq
      case "134G" => freq
      case "241G" => freq
      case "LIGHT" => freq

      case s: String ⇒
        s.toInt match {
          case khz if 1800 until 2000 contains khz => "160M"
          case khz if 3500 until 4000 contains khz => "80M"
          case khz if 7000 until 7300 contains khz => "40M"
          case khz if 14000 until 14350 contains khz => "20M"
          case khz if 21000 until 21450 contains khz => "15M"
          case khz if 28000 until 28700 contains khz => "10M"
          case _ =>
            throw new FreqException
        }
      case _ ⇒
        throw new FreqException
    }
  }
}


class FreqException() extends Exception("Invalid Frequency")
