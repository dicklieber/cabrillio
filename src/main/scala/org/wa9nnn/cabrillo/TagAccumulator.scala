
package org.wa9nnn.cabrillo

import org.wa9nnn.cabrillo.model.CabrilloTypes.Tag
import org.wa9nnn.cabrillo.model.TagValue

/**
 * Collects [[org.wa9nnn.cabrillo.model.TagValue]]s for one [[org.wa9nnn.cabrillo.model.CabrilloTypes#Tag]].
 *
 * @param tag that this will apply.
 */
class TagAccumulator(tag: Tag) {
  private val builder = Seq.newBuilder[TagValue]

  /**
   * Adds a [[org.wa9nnn.cabrillo.model.TagValue]] for this [[org.wa9nnn.cabrillo.model.CabrilloTypes#Tag]].
   * @param tagValue to add.
   */
  def apply(tagValue: TagValue): Unit = {
    builder += tagValue
  }

  def collect: Seq[TagValue] = {
    builder.result()
  }
}
