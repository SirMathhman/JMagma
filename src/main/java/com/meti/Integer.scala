package com.meti

import com.meti.Attribute.Name._

final class Integer(val input: Input) extends Token {
  @throws[AttributeException]
  override def apply(name: Attribute.Name): Attribute = name match {
    case Group => Token.Group.Integer
    case Content => new InputAttribute(input)
    case _ => throw AttributeException()
  }
}