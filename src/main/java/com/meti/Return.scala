package com.meti

import com.meti.Attribute.Name
import com.meti.Attribute.Name._

final class Return(val value: Token) extends Token {
  @throws[AttributeException]
  override def apply(name: Name): Attribute = name match {
    case Group => Token.Group.Returns
    case Value => TokenAttribute(value)
    case _ => throw AttributeException()
  }
}