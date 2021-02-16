package com.meti

object Strings {
  def stream(input: String): Stream[Char] = new StreamImpl(input)

  private class StreamImpl(val input: String) extends AbstractStream[Char] {
    private var counter = 0

    @throws[EndOfStreamException]
    override def head: Char = if (counter < input.length) {
      counter += 1
      input.charAt(counter - 1)
    }
    else throw EndOfStreamException("No more characters left.")
  }

}