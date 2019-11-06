
package org.wa9nnn.cabrillo.model

import org.wa9nnn.cabrillo.model.CabrilloTypes.Tag

case class ParsedCabrillo(data: Map[Tag, Seq[TagValue]]) {
  def apply(tag: Tag):Seq[TagValue] = {
   data.getOrElse(tag, Seq.empty)
  }
  def lineCount:Int = {
    var count = 0
    for {
      tagValues ← data.values
    } {
      count = count + tagValues.size
    }
    count
  }

}
