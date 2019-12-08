
package org.wa9nnn.cabrillo

import java.util.concurrent.atomic.AtomicInteger

import org.wa9nnn.cabrillo.model.CabrilloTypes.Tag
import org.wa9nnn.cabrillo.model.{CabrilloData, SimpleTagValue, TagValue}

/**
 * Used to programmatically create a [[org.wa9nnn.cabrillo.model.CabrilloData]].
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

  def toCabrilloData: CabrilloData = {
    accumulator(SimpleTagValue("END-OF-LOG", next()))
    CabrilloData(accumulator.result)
  }
}
