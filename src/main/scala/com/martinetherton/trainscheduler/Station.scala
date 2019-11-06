package com.martinetherton.trainscheduler

object Station {
  implicit def stringToStation(stationName: String): Station =
    new Station(stationName)
}

class Station(val name: String) extends AnyVal {

}
