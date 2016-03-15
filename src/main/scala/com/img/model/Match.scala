package com.img.model

import com.img.util.ReadUtils

import scala.collection.mutable.ListBuffer

/**
 * Created by prasadsriramula on 15/03/2016.
 */
case class Match() {

  var matchStates = new ListBuffer[DataEvent]()


  def getLastEvent : DataEvent = matchStates.lastOption.get

  def getLastEvents(n:Int) =matchStates.toList.takeRight(n)

  def getAllEventsSoFar = matchStates.toList


  def readMatchState(fileName:String): Unit ={
   println(s"Reading match event from file : $fileName ...")
   for ( dataEvent <- ReadUtils.readFileContent(fileName) ){

     try{
       val de = DataEvent.read(dataEvent)
       if(validateDataConsistancy(de)){
         matchStates += de
       }

       val addedEvent =getLastEvent
       println(s"Reading data Event for match clock in seconds:  "+ DataEvent.getMatchTime(addedEvent)+s" for raw data $dataEvent")
     }catch {
       case e: NumberFormatException        => println(s"invalid hexa decimal found $dataEvent")
       case e: InConsistentDataException    => println(s"Data Event not consistent with previous Match Data $dataEvent ") // in consistent data events here
       case e: Exception                    => println("IO exception")
     }

   }
  }


  /*

    Here some basic Data consistency rules assumed, please note that these rules can be improved based on individual property, limiting scope
    due to come up with a minimum viable product
    
    Best way would be to provider DataEvent equals , hashcode custom comparison methods based on the match time, individuals Scores 

      1. match time - Any new Data Event match time should be more than that of previous or greater than zero
      2. data event Score - the score should increase the score points for either team

   */

  @throws(classOf[InConsistentDataException])
  def validateDataConsistancy(de:DataEvent): Boolean ={
    if(matchStates.length >0 ){
      val lastDE = getLastEvent
      val lastDEBothTeamsScore    = DataEvent.getTeam1PointsTotal(lastDE)+DataEvent.getTeam2PointsTotal(lastDE) // total score of both teams in last Data Event
      val currentDEBothTeamsScore = DataEvent.getTeam1PointsTotal(de)+DataEvent.getTeam2PointsTotal(de) // total score of both teams in current Data Event

      if( 
        DataEvent.getMatchTime(lastDE) < DataEvent.getMatchTime(de)
        && lastDEBothTeamsScore < currentDEBothTeamsScore
         ){
        true
      }else{
        throw new InConsistentDataException("Data Event not consistent with previous Match Data")
      }
      
    }else{
      true
    }

  }


}

case class InConsistentDataException(message:String)  extends Exception(message)




