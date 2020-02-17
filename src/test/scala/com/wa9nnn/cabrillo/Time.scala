
package com.wa9nnn.cabrillo

import java.time.{LocalDateTime, ZonedDateTime}

object Time {



  def main(args: Array[String]): Unit = {
    val zdt = ZonedDateTime.parse( "2017-01-28 1910")
    println(zdt)
  }

}
