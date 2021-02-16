package com.meti

import com.meti.Attribute.Name
import com.meti.Attribute.Name._
import com.meti.Token.Type.Return

final class Return(val value: Token) extends Token {
  @throws[AttributeException]
  override def apply(name: Name): Attribute = name match {
    case Group => Return
    case Value => TokenAttribute(value)
    case _ => throw AttributeException()
  }
}