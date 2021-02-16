package com.meti

object StringRenderable {
  implicit def apply(value: String): StringRenderable = new StringRenderable(value)
}

class StringRenderable(value: String) extends Renderable {
  override def render: String = value
}