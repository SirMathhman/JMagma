package com.meti

object IndexException {
  def apply(message: String = null, cause: Throwable = null): IndexException = new IndexException(message, cause)
}

class IndexException(message: String, cause: Throwable) extends CollectionException(message, cause) {
}