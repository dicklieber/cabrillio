
package org.wa9nnn.cabrillo.model

import scala.util.{Failure, Try}

/**
 * type definitions.
 * Convenient short hand for some commonly used types
 */
object CabrilloTypes {
  type Tag = String
  type Tags = Seq[TagValue]
  type TryTags = Try[Tags]
  type TryTagsFailure = Failure[Tags]
}