
package com.wa9nnn.cabrillo

import java.io.PrintWriter

import com.wa9nnn.cabrillo.model.CabrilloData

/**
 * Renders a [[CabrilloData]] to text.
 */
object CabrilloWriter {
  def write(cabrilloData: CabrilloData, writer: PrintWriter): Unit = {
    cabrilloData.inLineOrder.foreach { tv => writer.println(tv.render) }
  }
}
