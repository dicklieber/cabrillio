
package org.wa9nnn.cabrillo.parsers

import org.wa9nnn.cabrillo.model.TagValue
import org.wa9nnn.cabrillo.model.CabrilloTypes._

trait TagParser {
  def tag: Tag

  def parse(lineNo: Int, tag: String, body: String): TagValue

}

object DefaultTagParser extends TagParser {
  override def tag: String = "*"

  override def parse(lineNo: Int, tag: Tag, body: String): TagValue = {
    AnyTag(tag, lineNo, body)
  }
}

case class AnyTag(tag: Tag, lineNumber: Int, body: String) extends TagValue {
  override def toString: String = body
}