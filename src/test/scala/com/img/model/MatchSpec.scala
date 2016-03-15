package com.img.model

import com.img.util.UnitSpec

/**
 * Created by prasadsriramula on 15/03/2016.
 */
class MatchSpec extends UnitSpec {


/*
 Reading from the sample file 1
 */

  val expLastDataEvent = DataEvent.read("0x12b0d8ea") // manually reading the file to create last item
  s"can parse incoming data items into a data structure suitable for capturing the state of the match from a self-consistent file " should
    s"return Match  with Data Event" in {

    val mat = new Match
    mat.readMatchState("/sample1.txt")

    val res= mat.getAllEventsSoFar.length

    res should be (28)


    val last = mat.getLastEvent
    last should be (expLastDataEvent)


  }





  /*
 Reading from the sample file 1
 */

  val expLastDataEvent2 = DataEvent.read("0x2ee74753") // manually reading the file to create last valid item
  s"can parse incoming data items into a data structure suitable for capturing the state of the match from a non self-consistent file " should
    s"return Match  with Data Event" in {

    val mat = new Match
    mat.readMatchState("/sample2.txt")

    val res= mat.getAllEventsSoFar.length

    res should be (17)


    val last = mat.getLastEvent
    last should be (expLastDataEvent2)


  }

}
