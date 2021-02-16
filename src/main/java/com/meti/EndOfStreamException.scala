package com.meti

object EndOfStreamException {
  def apply(message: String = null, cause: Throwable = null): EndOfStreamException = new EndOfStreamException(message, cause)
}

class EndOfStreamException(val message: String, val cause: Throwable) extends StreamException(message, cause) {
}