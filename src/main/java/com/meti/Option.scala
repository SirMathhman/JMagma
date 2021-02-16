package com.meti

trait Option[T] {
  def map[R](mapper: F1[T, R]): Option[R]

  def orElse(t: T): T
}