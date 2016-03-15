package com.img

import com.img.model.{DataEvent, Match}

/**
 * Created by prasadsriramula on 15/03/2016.
 */
object App {
  def main(args: Array[String]) ={

    for( file <- args){
      println(s"Starting reading Results from file $file... ")
      parseMatchData(file)
    }


  }

  def parseMatchData(fileName: String): Unit ={



    val mat = new Match
    mat.readMatchState(fileName)

    val lastScoreBy = DataEvent.getPropertyValue(mat.getLastEvent,DataEvent.whoScored)
    println("which team last scored :"+ DataEvent.getTeam(lastScoreBy))
    println("how many points :"+DataEvent.getPointsScored(mat.getLastEvent))
    val matchTime =DataEvent.getMatchTime(mat.getLastEvent)
    println("What point through the match :"+ matchTime/60+":"+matchTime%60 )
    println("What the resulting match score was :"+
      DataEvent.getTeam1PointsTotal(mat.getLastEvent) +"-" + DataEvent.getTeam2PointsTotal(mat.getLastEvent)
    )


  }
}

