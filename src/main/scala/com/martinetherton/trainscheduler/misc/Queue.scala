package com.martinetherton.trainscheduler.misc

object Queue {

  def apply[A](elements: Seq[A]): Queue[A] = {
    new Queue(elements)
  }

}

class Queue[A] private (val elements: Seq[A]) {

  override def equals(o: Any): Boolean = o match {
    case that: A => that == o
    case _ => false
  }

  override def hashCode(): Int = 41

  override def toString(): String = "elements are " + elements

    //f"$hours%02d:$minutes%02d"

  def dequeue(): (A, Queue[A]) = elements match {
    case Nil => throw new UnsupportedOperationException
    case head :: tail => (head ,new Queue(tail))
  }
}
