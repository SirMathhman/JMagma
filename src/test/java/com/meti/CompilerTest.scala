package com.meti

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CompilerTest {
  @Test
  @throws[CompileException]
  def empty(): Unit = assertEquals("", Compiler.compile(new Input("")).render)

  @Test
  @throws[CompileException]
  def test_main(): Unit = assertEquals("int main(){return 0;}", Compiler.compile(new Input("def main() : I16 => {return 0;}")).render)
}