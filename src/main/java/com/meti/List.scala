package com.meti

trait List[T] {
  @throws[CollectionException]
  def +(element: T): List[T]

  @throws[CollectionException]
  def set(index: Int, element: T): List[T]

  def stream: Stream[T]
}