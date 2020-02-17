
package com.wa9nnn.cabrillo

class CabrilloException(val lineNumber:Int, val name:String, val expected:String, val got:String, cause:Exception) extends Exception(cause){
  override def toString = s"CabrilloException($lineNumber, $name, $expected, $got)"
}
