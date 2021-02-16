package com.meti

case class TokenAttribute(value: Token) extends Attribute {
  @throws[AttributeException]
  override def computeToken: Token = value
}