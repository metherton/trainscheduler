package com.martinetherton.trainscheduler

case class Train(info: TrainInfo, schedule: Seq[(Time, Station)]) {

  require(schedule.size >= 2)

  // TODO: add check schedule is increasing in time
  require(Time.isIncreasing(schedule.map(slot => slot._1)))

  def c(slot: (Time, Station)): Station = slot._2

  val stations = schedule.map(slot => slot._2)
//  val stations = myMap(schedule, c, Nil)
  val backToBackStations: Seq[(Station, Station)] = stations zip stations.tail

  val departureTimes: Map[Station, Time] = schedule.map(slot => (slot._2, slot._1)).toMap

  def myMap(slots: Seq[(Time, Station)], f: ((Time, Station)) => Station, acc: Seq[Station]): Seq[Station] = slots match {
    case Nil => acc
    case h :: t => myMap(t, f, acc :+ f(h) )
  }

  def timeAt(station: Station): Option[Time] = {
    schedule.find(stop => stop._2 == station).map(found => found._1)
  }
}
