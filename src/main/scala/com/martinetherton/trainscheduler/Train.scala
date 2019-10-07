package com.martinetherton.trainscheduler

case class Train(info: TrainInfo, schedule: Seq[(Time, Station)]) {

  require(schedule.size >= 2)

  // TODO: add check schedule is increasing in time

  val stations = schedule.map(slot => slot._2)
}
