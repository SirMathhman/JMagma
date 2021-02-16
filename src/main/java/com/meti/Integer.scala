package com.meti

import com.meti.Attribute.Name._
import com.meti.Token.Type.Integer

final class Integer(val input: Input) extends Token {
  @throws[AttributeException]
  override def apply(name: Attribute.Name): Attribute = name match {
    case Group => Integer
    case Content => new InputAttribute(input)
    case _ => throw AttributeException()
  }
}