
package com.wa9nnn.cabrillo

import com.wa9nnn.cabrillo.model.TagValue
import com.wa9nnn.cabrillo.model.CabrilloTypes.Tag
import com.wa9nnn.cabrillo.model.TagValue

import scala.collection.concurrent.TrieMap

/**
 * Collects [[TagValue]]s organized by [[Tag]]
 */
class CabrilloAccumulator {
  private val tagAccumulators = new TrieMap[Tag, TagAccumulator]()

  def apply(value: TagValue): Unit = {
    tagAccumulators
      .getOrElseUpdate(value.tag, new TagAccumulator(value.tag))
      .apply(value)
  }

  def result: Map[String, Seq[TagValue]] = tagAccumulators.values.map(_.collect)
    .map(tv ⇒ tv.head.tag → tv)
    .toMap
}
