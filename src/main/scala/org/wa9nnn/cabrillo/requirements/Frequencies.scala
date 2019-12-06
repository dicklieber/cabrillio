
package org.wa9nnn.cabrillo.requirements

import com.typesafe.scalalogging.LazyLogging

/**
 * See http://wwrof.org/cabrillo/cabrillo-qso-templates/ "freq"
 */
object Frequencies extends LazyLogging {
  def check(freq: String): Unit = {
    logger.debug(s"Checking freq: $freq")
    freq match {
      case "50" =>
      case "70" =>
      case "144" =>
      case "222" =>
      case "432" =>
      case "902" =>
      case "1.2G" =>
      case "2.3G" =>
      case "3.4G" =>
      case "5.7G" =>
      case "10G" =>
      case "24G" =>
      case "47G" =>
      case "75G" =>
      case "123G" =>
      case "134G" =>
      case "241G" =>
      case "LIGHT" =>
      case s: String ⇒
        val khz = s.toInt
        if (
          (1800 until 2000).contains(khz) ||
            (3500 until 4000).contains(khz) ||
            (7000 until 7300).contains(khz) ||
            (14000 until 14350).contains(khz) ||
            (21000 until 21450).contains(khz) ||
            (28000 until 28700).contains(khz)
        ) {
          //ok don't throw
        } else {
          throw new FreqException
        }
      case _ ⇒
        throw new FreqException
    }
  }
}


class FreqException() extends Exception("Invalid Frequency")
