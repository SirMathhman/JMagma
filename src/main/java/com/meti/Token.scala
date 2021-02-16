package com.meti

object Token {

  sealed trait Group extends Attribute

  object Group {

    case object Integer extends Group

    case object Returns extends Group

  }

}

trait Token {
  @throws[AttributeException]
  def apply(name: Attribute.Name) : Attribute
}