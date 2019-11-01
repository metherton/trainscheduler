package com.martinetherton.trainscheduler.misc

  class Queue[A](val elements: Seq[A]) {

  override def equals(o: Any): Boolean = o match {
    case that: A => that == o
    case _ => false
  }

  override def hashCode(): Int = 41

  override def toString(): String = "elements are " + elements

    //f"$hours%02d:$minutes%02d"
}
