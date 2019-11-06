
package org.wa9nnn.cabrillo.requirements

import com.typesafe.scalalogging.LazyLogging
import org.wa9nnn.cabrillo.model.CabrilloTypes._
import org.wa9nnn.cabrillo.model.{ParsedCabrillo, TagValue}

trait Checker extends LazyLogging{
  def tag: Tag

  def check(parsedCabrillo: ParsedCabrillo): Seq[CabrilloError]

  def ok: Seq[CabrilloError] = {
    logger.debug("Ok")
    Seq.empty[CabrilloError]
  }

  def missingTag: Seq[CabrilloError] = {
    logger.debug("Missing")
    Seq(MissingTag(tag, s"No $tag in file!"))
  }

  def failure(tagValue: TagValue, cause: String): Seq[ValueError] = {
    logger.debug(s"failure: $cause")
    Seq(ValueError(tagValue.lineNumber, tagValue.body, tagValue.tag, cause))
  }

  def tooMany(lineNumbers: Seq[Int]): Seq[CabrilloError] = {
    logger.debug(s"too many: ${tag}s")
    Seq(TooManyTags(tag, lineNumbers))
  }
}

trait CabrilloError {
  def tag: Tag

  def cause: String
}

case class ValueError(lineNumber: Int, body: String, tag: Tag, cause: String) extends CabrilloError

case class MissingTag(tag: Tag, cause: String) extends CabrilloError

case class TooManyTags(tag: Tag, lineNumbers: Seq[Int]) extends CabrilloError {
  val cause: String = s"Too many $tag tags"
}
