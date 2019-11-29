
package org.wa9nnn.cabrillo.requirements

import org.wa9nnn.cabrillo.model.CabrilloTypes.Tag
import org.wa9nnn.cabrillo.model.TagValue

/**
 *
 * @param lineNumber 0 if not at a line e.g. missing tag, or line in file where error occurred.
 * @param body rvalue of a tag line. Always "" if lineNumber is 0.
 * @param tag of interest.
 * @param cause description of what is wrong.
 */
case class CabrilloError(val lineNumber: Int, val body: String, tag: Tag, cause: String)

object CabrilloError {
  /**
   * Convenience method to create an error for a [[TagValue]].
   * @param tagValue with the error
   * @param cause description.
   */
  def apply(tagValue: TagValue, cause: String): CabrilloError = {
    new CabrilloError(lineNumber = tagValue.lineNumber,
      body = tagValue.body,
      tag = tagValue.tag,
      cause = cause
    )
  }
  /**
   * Convenience method to create an internal exception for a [[TagValue]].
   * @param tagValue with the error
   * @param cause cause.
   */
  def apply(tagValue: TagValue, cause: Exception): CabrilloError = {
    new CabrilloError(lineNumber = tagValue.lineNumber,
      body = tagValue.body,
      tag = tagValue.tag,
      cause = cause.toString
    )
  }

  /**
   * Create a missing tag error. lineNumber is 0 body is empty.
   * @param tag that was not found.
   */
  def apply(tag: Tag): CabrilloError = {
    new CabrilloError(0, "", tag, s"Missing tag: $tag")
  }
}





