
package org.wa9nnn.cabrillo.requirements

import org.wa9nnn.cabrillo.model.Cabrillo

case class CheckEngine(requiredTags: RequiredTags)(implicit contestInfo: ContestInfo) {
  def check(cabrillo: Cabrillo): Seq[CabrilloError] = {
    cabrillo.tags.flatMap { tag ⇒
      val handler = requiredTags.handler(tag)
      handler.check(cabrillo)
    }
  }
}
