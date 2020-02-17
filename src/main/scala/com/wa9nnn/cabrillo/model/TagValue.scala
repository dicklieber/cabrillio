
package com.wa9nnn.cabrillo.model

import com.wa9nnn.cabrillo.model.CabrilloTypes._
import com.wa9nnn.cabrillo.parsers.LineBody
import com.wa9nnn.cabrillo.requirements.{CabrilloError, ContestInfo}

/**
 * A line that was parsed.
 */
trait TagValue {
  def check()(implicit contestInfo: ContestInfo): Seq[CabrilloError]

  def tag: Tag

  def lineNumber: Int

  def body: String

  def render: String = {
    if(body.isEmpty){
      s"$tag:"
    }else{
      s"$tag: $body"
    }
  }
  def withLineNumber(lineNumber:Int): TagValue
}

/**
 * This has the value for most tags other than QSO.
 *
 * @param tag        lValue
 * @param lineNumber where it occured within the file.
 * @param body       rValue
 */
case class SimpleTagValue(tag: Tag, lineNumber: Int, body: String = "") extends TagValue {
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

  override def withLineNumber(lineNumber: Int): TagValue = {
    copy(lineNumber=lineNumber)
  }
}