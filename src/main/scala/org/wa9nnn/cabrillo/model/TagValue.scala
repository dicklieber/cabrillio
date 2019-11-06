
package org.wa9nnn.cabrillo.model
import org.wa9nnn.cabrillo.model.CabrilloTypes._

trait TagValue {
  def tag:Tag
  def lineNumber: Int
  def body: String
}
