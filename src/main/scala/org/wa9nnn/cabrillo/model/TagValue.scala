
package org.wa9nnn.cabrillo.model
import org.wa9nnn.cabrillo.model.CabrilloTypes._
import org.wa9nnn.cabrillo.requirements.{CabrilloError, ContestInfo}

trait TagValue {
  def check()(implicit contestInfo: ContestInfo): Seq[CabrilloError]

  def tag:Tag
  def lineNumber: Int
  def body: String
}
