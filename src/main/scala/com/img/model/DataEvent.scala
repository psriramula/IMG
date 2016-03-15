package com.img.model

import com.img.util.ReadUtils._

/**
 * Created by prasadsriramula on 14/03/2016.
 */
case class DataEvent (val dataEventPattern:String)

 object DataEvent{

  /*
   returns a DataEvent instance for a given data event in Hexadecimal
   dataEventInHEX - a data event string in hexadecimal notation
  */

  def read(dataEventInHEX:String): DataEvent ={
    val binaryDataEvent= castHexToBinary(dataEventInHEX)
    new DataEvent(getEmptyCharsToFillDataEvent(binaryDataEvent))
  }

  val dePatternLength = 32 // Standard Data Event size

   """
     |0             - unUsedChar
     |000000001111  - matchTime
     |00000010      - team1PointsTotal
     |00000000      - team2PointsTotal
     |0             - whoScored
     |10            - pointsScored
     |"""

   /*
     DataEvent Binary Data representation by following above notation
    */


   val unUsedChar       = (0,1)
   val matchTime        = (1,12)
   val team1PointsTotal = (13,8)
   val team2PointsTotal = (21,8)
   val whoScored        = (29,1)
   val pointsScored     = (30,2)

   /*
    returns the Data Event property
    */

   def getPropertyValue(de:DataEvent, property:(Int,Int)):String ={
     de.dataEventPattern.substring(property._1,(property._1+property._2))
   }

   def getTeam1PointsTotal(de:DataEvent):Int ={
     castBinaryToInt( getPropertyValue(de,team1PointsTotal))
   }

   def getTeam2PointsTotal(de:DataEvent):Int ={
     castBinaryToInt( getPropertyValue(de,team2PointsTotal))
   }
   def getMatchTime(de:DataEvent):Int ={
     castBinaryToInt( getPropertyValue(de,matchTime))
   }





   /*
   A utility method which fills 0 'zero character' in preceding empty characters after conversion
   */
  def getEmptyCharsToFillDataEvent(rawDataEventString: String) :String ={
    val unfilledCharsLength =dePatternLength-rawDataEventString.length
    "".padTo(unfilledCharsLength,'0') + rawDataEventString
  }


}
