package com.meti

object RenderException {
  def apply(message: String = null, cause: Throwable = null): RenderException = new RenderException(message, cause)
}

class RenderException(message: String, cause: Throwable) extends CompileException(message, cause) {
}