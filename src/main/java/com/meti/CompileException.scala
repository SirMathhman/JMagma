package com.meti

object CompileException {
  def apply(message: String = null, cause: Throwable = null): CompileException = new CompileException(message, cause)
}

class CompileException(message: String, cause: Throwable) extends Exception(message, cause) {
}