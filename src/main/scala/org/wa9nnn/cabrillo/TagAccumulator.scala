
package org.wa9nnn.cabrillo

import org.wa9nnn.cabrillo.model.CabrilloTypes.Tag
import org.wa9nnn.cabrillo.model.TagValue

/**
 * Collects [[TagValue]]s for one [[Tag]].
 *
 * @param tag that this will accumulate.
 */
class TagAccumulator(tag: Tag) {
  private val builder = Seq.newBuilder[TagValue]

  /**
   * Adds a [[TagValue]] for this [[Tag]].
   * @param tagValue to add.
   */
  def apply(tagValue: TagValue): Unit = {
    builder += tagValue
  }

  def collect: Seq[TagValue] = {
    builder.result()
  }
}
