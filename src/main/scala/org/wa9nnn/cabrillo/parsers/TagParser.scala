
package org.wa9nnn.cabrillo.parsers

import org.wa9nnn.cabrillo.model.CabrilloTypes._
import org.wa9nnn.cabrillo.model.TagValue
import org.wa9nnn.cabrillo.requirements.{CabrilloError, ContestInfo}

trait TagParser {

  def tag: Tag

  def parse(lineBody: LineBody): TagValue

}
case class LineBody(lineNumber:Int, body:String)

class DefaultTagParser(val tag: Tag) extends TagParser {

  override def parse(lineBody: LineBody): TagValue = {
    new SimpleTag(tag, lineBody)
  }
}

case class SimpleTag(tag: Tag, lineNumber: Int, body: String) extends TagValue {
  def this(tag:Tag, lineBody:LineBody){
    this(tag, lineBody.lineNumber, lineBody.body)
  }
  override def toString: String = body

  /**
   * Nothing to check in a [[SimpleTag]] any value is ok.]
   * @return no errors.
   */
  override def check()(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    Seq.empty
  }
}