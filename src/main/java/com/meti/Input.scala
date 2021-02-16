package com.meti

class Input(val input: String) {
  def asOutput = new StringOutput(getInput)

  def isEmpty = getInput.isBlank

  def getInput = input

  def slice(extent: Int) = {
    val slice = getInput.substring(extent)
    val value = slice.trim
    new Input(value)
  }

  def stream: Stream[Char] = Strings.stream(getInput)

  def test = getInput.startsWith("return ")
}