
package org.wa9nnn.cabrillo.requirements

import org.wa9nnn.cabrillo.model.CabrilloTypes.Tag
import org.wa9nnn.cabrillo.model.{Cabrillo, TagValue}


abstract class CabrilloError(tag: Tag, cause: String)

abstract class ErrorAtLine(val lineNumber: Int, val body: String, tag: Tag, cause: String) extends CabrilloError(tag, cause)


case class ValueErrorAtLine(override val lineNumber: Int, override val body: String, tag: Tag, cause: String) extends ErrorAtLine(lineNumber, body, tag, cause) {
  def this(tagValue: TagValue, cause: String) {
    this(tagValue.lineNumber, tagValue.body, tagValue.tag, cause)
  }
}

case class MissingTag(tag: Tag) extends CabrilloError(tag, "Required tag is missing")




