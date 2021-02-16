package com.meti

case class Some[T](value: T) extends Option[T] {
  override def orElse(t: T): T = value

  override def map[R](mapper: F1[T, R]): Option[R] = Some(mapper(value))
}