package com.meti

object AttributeException {
  def apply(message: String = null, cause: Throwable = null): AttributeException = new AttributeException(message, cause)
}

class AttributeException(message: String, cause: Throwable) extends CompileException(message, cause) {
}