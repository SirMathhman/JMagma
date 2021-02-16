package com.meti

import com.meti.ListOutput.apply

class RenderableOutput(val renderable: Renderable) extends Output {
  override def concat(other: Renderable): Output = apply
    .append(renderable)
    .append(other)
    .complete

  override def concat(other: String): Output = apply
    .append(renderable)
    .append(new StringRenderable(other))
    .complete

  override def render: String = renderable.render
}