
package org.wa9nnn.cabrillo.model

import org.wa9nnn.cabrillo.model.CabrilloTypes.Tag

import scala.util.{Failure, Try}

object CabrilloTypes {
  type Tag = String
  type Tags = Seq[TagValue]
  type TryTags = Try[Tags]
  type TryTagsFailure = Failure[Tags]

}