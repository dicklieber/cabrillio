
package com.wa9nnn.cabrillo

import java.util.concurrent.atomic.AtomicInteger

import com.wa9nnn.cabrillo.model.{CabrilloData, SimpleTagValue, TagValue}
import com.wa9nnn.cabrillo.model.CabrilloTypes.Tag
import com.wa9nnn.cabrillo.model.{CabrilloData, SimpleTagValue, TagValue}

/**
 * Used to programmatically create a [[CabrilloData]].
 * This will automatically add START-OF-LOG and END-OF-LOG
 * Line numbers are automatically added as data is entered
 */
class Builder(version: String = "3.0") {
  private val lineNumber = new AtomicInteger()

  private def next(): Int = {
    lineNumber.getAndIncrement()
  }

  private val accumulator = new CabrilloAccumulator
  accumulator(SimpleTagValue("START-OF-LOG", next(), version))

  def +(tag: Tag, body: String): Builder = {
    accumulator(SimpleTagValue(tag, next(), body))
    this
  }
 def +(tv:TagValue): Builder = {
    accumulator(tv.withLineNumber(next()))
    this
  }

  /**
   * Removes all instances of the tag.
   * @param tag to be removed.
   */
  def delete(tag: Tag):Unit = accumulator.delete(tag)

  def toCabrilloData: CabrilloData = {
    accumulator(model.SimpleTagValue("END-OF-LOG", next()))
    model.CabrilloData(accumulator.result)
  }
}
