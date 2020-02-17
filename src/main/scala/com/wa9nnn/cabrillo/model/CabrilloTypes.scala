
package com.wa9nnn.cabrillo.model

import scala.util.{Failure, Try}

/**
 * type definitions.
 * Convenient short hand for some commonly used types
 */
object CabrilloTypes {
  type Tag = String
  type TagValues = Seq[TagValue]
  type TryTags = Try[TagValues]
  type TryTagsFailure = Failure[TagValues]
}