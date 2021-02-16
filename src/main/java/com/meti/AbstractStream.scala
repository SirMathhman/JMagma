package com.meti

import java.lang

abstract class AbstractStream[T] extends Stream[T] {
  override def fold[R](initial: R, folder: F2[R, T, R]): R = ???

  override def fold(folder: F2[T, T, T]): Option[T] = ???

  override def map[R](mapper: F1[T, R]): Stream[R] = ???

  override def allMatch(predicate: F1[T, lang.Boolean]): Boolean = {
    var continue = true
    while (continue)
      try if (!predicate.apply(head))
        return false
      catch {
        case _: EndOfStreamException => continue = false
      }
    true
  }

  override def mapE1[R](mapper: F1E1[T, R, _]): AbstractStream[R] = new AbstractStream[R]() {
    @throws[StreamException]
    override def head: R = try mapper.apply(AbstractStream.this.head())
    catch {
      case e: EndOfStreamException => throw e
      case e: Exception => throw new StreamException(e)
    }
  }
}