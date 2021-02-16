package com.meti

class None[T]() extends Option[T] {
  override def orElse(t: T): T = t

  def apply[R](): None[R] = new None()

  override def map[R](mapper: F1[T, R]): Option[R] = new None[R]
}