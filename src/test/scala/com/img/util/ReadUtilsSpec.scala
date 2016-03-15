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


  val binary = "00000000"
  val expectedInt = 0
  "castBinaryToInt in ReadUtils" should s"return $expectedInt for a given $binary" in{
    val res = ReadUtils.castBinaryToInt(binary)

    res should be (expectedInt)
  }


  val binary2 = "00000010"
  val expectedInt2 = 2
  "castBinaryToInt in ReadUtils" should s"return $expectedInt2 for a given $binary2" in{
    val res = ReadUtils.castBinaryToInt(binary2)

    res should be (expectedInt2)
  }

}
