package com.meti

import org.junit.jupiter.api.Assertions.assertEquals

object MagmaAssertions {
  @throws[CompileException]
  def assertCompile(expected: String, source: String): Unit = {
    val input = new Input(source)
    val output = Compiler.compile(input)
    assertEquals(expected, output.render())
  }
}