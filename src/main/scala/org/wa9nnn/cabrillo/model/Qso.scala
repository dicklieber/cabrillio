
package org.wa9nnn.cabrillo.model

import java.time.ZonedDateTime

trait Qso extends TagValue{
  def freq:String
  def mode:String
  def stamp:ZonedDateTime
  def sent:Exchange
  def received:Exchange
}

trait Exchange {

}