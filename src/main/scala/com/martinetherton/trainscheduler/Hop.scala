package com.martinetherton.trainscheduler

import scala.util.Success

case class Hop(from: Station, to: Station, train: Train) {

  require(from != to, "from cannot be equal to to")
  require(train.backToBackStations.contains((from, to)))

  val departureTime: Time = ((train.departureTimes.find(slot => slot._1 == from)).get)._2
  val arrivalTime: Time = ((train.departureTimes.find(slot => slot._1 == to)).get)._2

}
