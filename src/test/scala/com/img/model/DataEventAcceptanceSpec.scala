package com.img.model

import com.img.util.UnitSpec

/**
 * Created by prasadsriramula on 14/03/2016.
 */
class DataEventAcceptanceSpec extends UnitSpec {

  /*
   Validating parsed data
  */

  /*
  If after 15 seconds of play,
  Team 1 scores 2 points,
  then the following will be received:
  0x781002 = 7868418 = 0 000000001111 00000010 00000000 0 10
   */
  val rawDataEventHex1 = "0x781002"
  val matchClock1 = 15
  val score1 =2

  s"If after 15 seconds of play, Team 1 scores $score1 points with $rawDataEventHex1 " should s"then the following will be received" in {

    val de = DataEvent.read(rawDataEventHex1)


    val matchClock1 = DataEvent.getMatchTime(de)
    matchClock1 should be(matchClock1) // 15 seconds

    val team1Score = DataEvent.getTeam1PointsTotal(de)
    team1Score should be(score1) // 2 points
  }


  /*

  If 15 seconds later,
  Team 2 replies with 3 points,
  then the following will be received:
  0xf0101f = 15732767 = 0 000000011110 00000010 00000011 1 11
   */
  val rawDataEventHex2 = "0xf0101f"
  val matchClock2 = matchClock1+15  //15 seconds later,
  val score2 =3

  s"If 15 seconds later, Team 2 replies with $score2 points $rawDataEventHex2 " should s"then the following will be received" in {

    val de = DataEvent.read(rawDataEventHex2)


    val matchClock = DataEvent.getMatchTime(de)
    matchClock should be(matchClock2) // 30 seconds

    val team1Score = DataEvent.getTeam1PointsTotal(de)
    team1Score should be(2) // 2 points - team 1 not scored

    val team2Score = DataEvent.getTeam2PointsTotal(de)
    team2Score should be(score2) // 2 points


  }




}
