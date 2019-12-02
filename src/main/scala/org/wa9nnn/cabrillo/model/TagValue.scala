
package org.wa9nnn.cabrillo.model
import org.wa9nnn.cabrillo.model.CabrilloTypes._
import org.wa9nnn.cabrillo.parsers.LineBody
import org.wa9nnn.cabrillo.requirements.{CabrilloError, ContestInfo}

/**
 * A line that was parsed.
 */
trait TagValue {
  def check()(implicit contestInfo: ContestInfo): Seq[CabrilloError]

  def tag:Tag
  def lineNumber: Int
  def body: String
}

case class SimpleTagValue(tag: Tag, lineNumber: Int, body: String) extends TagValue {
  def this(tag: Tag, lineBody: LineBody) {
    this(tag, lineBody.lineNumber, lineBody.body)
  }

  override def toString: String = body

  /**
   * Nothing to check in a [[SimpleTagValue]] any value is ok.]
   *
   * @return no errors.
   */
  override def check()(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    Seq.empty
  }
}