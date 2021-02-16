package com.meti

object StreamException {
  def apply(message: String = null, cause: Throwable = null): StreamException = new StreamException(message, cause)
}

class StreamException(message: String, cause: Throwable) extends Exception(message, cause) {
}