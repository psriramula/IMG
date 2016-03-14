package com.img.util

/**
 * Created by prasadsriramula on 12/03/2016.
 */
class ReadUtilsSpec extends UnitSpec{

  val content=
    List("line1",
      "line2",
      "line3"
    )
  "readFileContent in ReadUtils" should s"return $content" in {
    val res = ReadUtils.readFileContent("/test.txt")

    res should be (content)
  }

  val binaryResult ="1101"
  "toBinary in ReadUtils" should s"return $binaryResult" in{
    val res = ReadUtils.toBinary(13)

    res should be (binaryResult)
  }

  val hexValue ="0x781002"
  val binResultFromHex="11110000001000000000010"
  "castHexToBinary in ReadUtils" should s"return $binResultFromHex for a given $hexValue" in{
    val res = ReadUtils.castHexToBinary(hexValue)

    res should be (binResultFromHex)
  }



}
