
package com.wa9nnn.cabrillo

import com.wa9nnn.cabrillo.model.{CabrilloTypes, TagValue}
import com.wa9nnn.cabrillo.model.CabrilloTypes.Tag
import com.wa9nnn.cabrillo.model.TagValue

/**
 * Collects [[TagValue]]s for one [[CabrilloTypes#Tag]].
 *
 * @param tag that this will apply.
 */
class TagAccumulator(tag: Tag) {
  private val builder = Seq.newBuilder[TagValue]

  /**
   * Adds a [[TagValue]] for this [[CabrilloTypes#Tag]].
   * @param tagValue to add.
   */
  def apply(tagValue: TagValue): Unit = {
    builder += tagValue
  }

  def collect: Seq[TagValue] = {
    builder.result()
  }
}
