package com.meti

object CollectionException {
  def apply(message: String = null, cause: Throwable = null): CollectionException = new CollectionException(message, cause)
}

class CollectionException(message: String, cause: Throwable) extends Exception(message, cause) {
}