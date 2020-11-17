package com.meti.api.core

import java.util.Optional
import java.util.function.Function
import java.util.function.Predicate
import java.util.function.Supplier

trait Option[T] {
  def toJava : Optional[T]

  def filter(predicate: Predicate[T]) : Option[T]

  def orElseSupply(supplier: Supplier[T]) : T

  def flatMap[R](function: Function[T, Option[R]]) : Option[R]

  def map[R](mapper: Function[T, R]) : Option[R]

  def orElse(other: T) : T
}