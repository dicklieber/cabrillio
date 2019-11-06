
package org.wa9nnn.cabrillo.parsers

import org.wa9nnn.cabrillo.model.Value

trait TagParser {
  def tag: String

  def parse(lineNo: Int, tag: String, body: String): Value

}

object DefaultTagParser extends TagParser {
  override def tag: String = "*"

  override def parse(lineNo: Int, tag: String, body: String): Value = {
    AnyTag(tag, lineNo, body)
  }
}

case class AnyTag(tag: String, lineNumber: Int, body: String) extends Value {
  override def toString: String = body
}