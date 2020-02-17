
package com.wa9nnn.cabrillo.contests

/**
  * One ARRL section
  * @param name user friendly name.
  * @param code actual code
  * @param area callsign area
  */
case class ArrlSection(name: String, code: String, area: String) extends Ordered[ArrlSection] {
  override def compare(that: ArrlSection): Int = this.code compareTo that.code
}
