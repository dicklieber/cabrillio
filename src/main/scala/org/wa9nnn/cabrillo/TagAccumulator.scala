
package org.wa9nnn.cabrillo

import org.wa9nnn.cabrillo.model.Value

class TagAccumulator(tag:String) {
  private val builder = Seq.newBuilder[Value]

  def apply(item: Value): Unit = {
    builder += item
  }

  def collect: Seq[Value] = {
    builder.result()
  }
}
