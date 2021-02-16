package com.meti

import java.nio.file.{Files, Paths}

object Main {
  def main(args: Array[String]): Unit = try {
    val source = Paths.get(".", "Main.mg")
    val input = Files.readString(source)
    val target = Paths.get(".", "Main.c")
    Files.writeString(target, Compiler.compile(new Input(input)).render)
  } catch {
    case e: Exception => e.printStackTrace()
  }
}