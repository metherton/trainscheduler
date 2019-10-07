package com.martinetherton.trainscheduler

class JourneyPlanner(val trains: Set[Train]) {

  //Train(number: Int, kind: String, schedule: Seq[(Time, Station)]) {

  val stations: Set[Station] = trains.flatMap(train => train.stations)

  def trainsAt(station: Station): Set[Train] = trains.filter(train => train.stations.contains(station))

  def stopsAt(station: Station): Set[(Time, Train)] =
    for {
      train <- trains
      slot <- train.schedule if station == slot._2
    } yield (slot._1, train)

  def isShortTrip(from: Station, to: Station): Boolean =
    trains.exists(train =>
      train.stations.dropWhile(station => station != from) match {
        case `from` +: `to` +: _      => true
        case `from` +: _ +: `to` +: _ => true
        case _                        => false
      }
    )

}
