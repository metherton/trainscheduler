package com.martinetherton.trainscheduler

class JourneyPlanner(val trains: Set[Train]) {

  //Train(number: Int, kind: String, schedule: Seq[(Time, Station)]) {

  val stations: Set[Station] = trains.flatMap(train => train.stations)

  val departureStations: Set[Station] = for {
    train <- trains
    backToBack <- train.backToBackStations
  } yield backToBack._1

  val hops: Map[Station, Set[Hop]] = {
    departureStations.map(station => (station, for {
      train <- trainsAt(station)
      hop <-  train.backToBackStations if hop._1 == station
    } yield Hop(hop._1, hop._2, train))).toMap
  }

  def connections(from: Station, to: Station, departureTime: Time): Set[Seq[Hop]] =  {
    require(from != to, "from cannot be equal to to")
    for {
      hop <- hops(from).filter(h => h.departureTime >= departureTime)
      hopTo <- if (hop.to != to) connections(hop.to, to, hop.arrivalTime) else Set(List())
    } yield hop +: hopTo
  }

  def trainsAt(station: Station): Set[Train] = trains.filter(train => train.stations.contains(station))

  def stopsAt(station: Station): Set[(Time, Train)] =
    for {
      train <- trains
      time <- train.timeAt(station)
    } yield (time, train)

  def isShortTrip(from: Station, to: Station): Boolean =
    trains.exists(train =>
      train.stations.dropWhile(station => station != from) match {
        case `from` +: `to` +: _      => true
        case `from` +: _ +: `to` +: _ => true
        case _                        => false
      }
    )

}
