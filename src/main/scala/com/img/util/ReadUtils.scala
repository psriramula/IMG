package com.img.util

import scala.io.Source

/**
 * Created by prasadsriramula on 12/03/2016.
 */
object ReadUtils {

  def toBinary(n: Int): String = n match {
    case 0|1 => s"$n"
    case _   => s"${toBinary(n/2)}${n%2}"

  }

  def castHexToBinary(s: String) = {
    val Hex = "(0x)?([0-9a-fA-F]+)".r
    s match {
      case Hex(_,s) => toBinary(Integer.parseInt(s,16))
      case _ => throw new NumberFormatException("Invalid hexadecimal number: "+s)
    }
  }

  def readFileContent(fileName:String)  = {
    Source.fromFile(getClass.getResource(fileName).getFile).getLines.toList
  }






}
