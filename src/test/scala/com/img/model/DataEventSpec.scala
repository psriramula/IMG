package com.img.model

import com.img.util.{ UnitSpec}

/**
 * Created by prasadsriramula on 14/03/2016.
 */
class DataEventSpec extends UnitSpec {

  val rawDataEventHex1 ="0x781002"
  val expectedDataEvent1 ="00000000011110000001000000000010"

  s"creating a  DataEvent with $rawDataEventHex1 " should s"return DataEvent with $expectedDataEvent1" in {

    val de= DataEvent.read(rawDataEventHex1)
    val result = de.dataEventPattern

    result should be (expectedDataEvent1)
  }

  val rawDataEventHex2 ="0xf0101f"
  val expectedDataEvent2 ="00000000111100000001000000011111"

  s"creating a  DataEvent with $rawDataEventHex2 " should s"return DataEvent with $expectedDataEvent2" in {

    val de= DataEvent.read(rawDataEventHex2)
    val result = de.dataEventPattern

    result should be (expectedDataEvent2)
  }


  val rawDataEventHex3 ="0x1310c8a1"
  val expectedDataEvent3 ="00010011000100001100100010100001"

  s"creating a  DataEvent with $rawDataEventHex3 " should s"return DataEvent with $expectedDataEvent3" in {

    val de= DataEvent.read(rawDataEventHex3)
    val result = de.dataEventPattern

    result should be (expectedDataEvent3)
  }

  val rawDataEventHex4 ="0x29f981a2"
  val expectedDataEvent4 ="00101001111110011000000110100010"

  s"creating a  DataEvent with $rawDataEventHex4 " should s"return DataEvent with $expectedDataEvent4" in {

    val de= DataEvent.read(rawDataEventHex4)
    val result = de.dataEventPattern

    result should be (expectedDataEvent4)
  }

  val rawDataEventHex5 ="0x48332327"
  val expectedDataEvent5 ="01001000001100110010001100100111"

  s"creating a  DataEvent with $rawDataEventHex5" should s"return DataEvent with $expectedDataEvent5" in {

    val de= DataEvent.read(rawDataEventHex5)
    val result = de.dataEventPattern

    result should be (expectedDataEvent5)
  }





}
