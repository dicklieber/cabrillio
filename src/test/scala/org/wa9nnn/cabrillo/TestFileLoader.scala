
package org.wa9nnn.cabrillo

import org.wa9nnn.cabrillo.model.CabrilloData

import scala.io.Source

object TestFileLoader {
  def apply(fileName: String = "wfd1.cbr"): CabrilloData = {
    val parseEngine = new Parser
    val bufferedSource = Source.fromResource("wfd1.cbr")
    parseEngine.parse(bufferedSource)

  }
}
