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

  s"If 15 seconds later, Team 2 replies with 3 points $rawDataEventHex2 " should s"then the following will be received" in {

    val de = DataEvent.read(rawDataEventHex2)


    val matchClock = DataEvent.getMatchTime(de)
    matchClock should be(matchClock2) // 30 seconds

    val dataEventScore =DataEvent.getPointsScored(de)
    dataEventScore should be (score2) // 1

    val team1Score = DataEvent.getTeam1PointsTotal(de)
    team1Score should be(2) // 2 points - team 1 not scored

    val team2Score = DataEvent.getTeam2PointsTotal(de)
    team2Score should be(3) // 2 points


  }


  /*
    Other sample data points:
    0x1310c8a1 - At 10:10, a single point for Team 1 gives them a 5 point lead – 25-20
   */

  val rawDataEventHex3 = "0x1310c8a1"
  val matchClock3 = 610  //At 10:10,

  val score3 = 1 //a single point

  s"At 10:10, a single point for Team 1 gives them a 5 point lead – 25-20 " +
    s"for data event $rawDataEventHex3 " should s"then the following will be received" in {

    val de = DataEvent.read(rawDataEventHex3)

    val dataEventScore =DataEvent.getPointsScored(de)
    dataEventScore should be (score3) // 1

    val matchClock = DataEvent.getMatchTime(de)
    matchClock should be(matchClock3) // 610 seconds

    val team1Score = DataEvent.getTeam1PointsTotal(de)
    team1Score should be(25)

    val team2Score = DataEvent.getTeam2PointsTotal(de)
    team2Score should be(20)


  }

  /*
  0x29f981a2 - At 22:23, a 2-point shot for Team 1 leaves them 4 points behind at 48-52
   */

  val rawDataEventHex4 = "0x29f981a2"
  val matchClock4 = 1343  //At 22:23,

  val score4 = 2 //a 2-point

  s"At 22:23, a 2-point shot for Team 1 leaves them 4 points behind at 48-52" +
    s"for data event $rawDataEventHex4 " should s"then the following will be received" in {

    val de = DataEvent.read(rawDataEventHex4)

    val dataEventScore =DataEvent.getPointsScored(de)
    dataEventScore should be (score4)

    val matchClock = DataEvent.getMatchTime(de)
    matchClock should be(matchClock4)

    val team1Score = DataEvent.getTeam1PointsTotal(de)
    team1Score should be(48)

    val team2Score = DataEvent.getTeam2PointsTotal(de)
    team2Score should be(52)

  }


  /*
  0x48332327 - At 38:30, a 3-point shot levels the game for Team 2 at 100 points each
   */

  val rawDataEventHex5 = "0x48332327"
  val matchClock5 = 2310  //At 22:23,

  val score5 = 3 //a 3-point

  s"At 38:30, a 3-point shot levels the game for Team 2 at 100 points each" +
    s"for data event $rawDataEventHex5 " should s"then the following will be received" in {

    val de = DataEvent.read(rawDataEventHex5)

    val dataEventScore =DataEvent.getPointsScored(de)
    dataEventScore should be (score5)

    val matchClock = DataEvent.getMatchTime(de)
    matchClock should be(matchClock5)

    val team1Score = DataEvent.getTeam1PointsTotal(de)
    team1Score should be(100)

    val team2Score = DataEvent.getTeam2PointsTotal(de)
    team2Score should be(100)

  }


}
