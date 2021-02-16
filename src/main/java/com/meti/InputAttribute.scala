package com.meti

final class InputAttribute(val input: Input) extends Attribute {
  @throws[AttributeException]
  override def computeInput: Input = input
}