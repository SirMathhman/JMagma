package com.meti

import com.meti.StringRenderable.apply

class StringOutput(val value: String) extends Output {
  override def concat(other: Renderable): Output = ListOutput
    .append(value)
    .append(other)
    .complete

  override def concat(other: String): Output = ListOutput.append(value)
    .append(other)
    .complete

  override def render: String = value
}