
package com.wa9nnn.cabrillo.parsers

import com.wa9nnn.cabrillo.model.{SimpleTagValue, TagValue}
import com.wa9nnn.cabrillo.model.CabrilloTypes._
import com.wa9nnn.cabrillo.model.{SimpleTagValue, TagValue}

trait TagParser {

  def tag: Tag

  def parse(lineBody: LineBody): TagValue

}

case class LineBody(lineNumber: Int, body: String) {
  def this(body: String) {
    this(0, body)
  }
}

/**
 * Used for simple string valued tags.
 */
class DefaultTagParser(val tag: Tag) extends TagParser {

  override def parse(lineBody: LineBody): TagValue = {
    new SimpleTagValue(tag, lineBody)
  }
}

