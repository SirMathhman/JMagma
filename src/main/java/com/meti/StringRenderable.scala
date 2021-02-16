package com.meti

case class StringRenderable(value: String) extends Renderable {
  override def render: String = value
}