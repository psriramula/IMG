package com.img.model

import com.img.util.ReadUtils

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
    val binaryDataEvent= ReadUtils.castHexToBinary(dataEventInHEX)
    new DataEvent(getEmptyCharsToFillDataEvent(binaryDataEvent))
  }

  val dePatternLength = 32 // Standard Data Event size
  /*
   A utility method which fills 0 'zero character' in preceding empty characters after conversion
   */
  def getEmptyCharsToFillDataEvent(rawDataEventString: String) :String ={
    val unfilledCharsLength =dePatternLength-rawDataEventString.length
    "".padTo(unfilledCharsLength,'0') + rawDataEventString
  }


}
