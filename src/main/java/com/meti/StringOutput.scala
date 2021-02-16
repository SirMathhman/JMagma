package com.meti

class StringOutput(val value: String) extends Output {
  override def concat(other: Renderable): Output = ListOutput.apply.append(StringRenderable(value))
    .append(other).complete

  override def concat(other: String): Output = ListOutput.apply.append(new StringRenderable(value)).append(StringRenderable(other)).complete

  override def render: String = value
}