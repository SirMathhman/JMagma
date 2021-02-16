package com.meti

import com.meti.StringRenderable.apply

class Builder(val renderables: List[Renderable] = ArrayList.empty) {
  def append(renderable: Renderable) = new Builder(renderables.+(renderable))

  def complete = new ListOutput(renderables)
}

object ListOutput extends Builder

case class ListOutput(renderables: List[Renderable]) extends Output {
  override def concat(other: Renderable) = new ListOutput(renderables + other)
  override def concat(other: String) = new ListOutput(renderables + other)

  @throws[RenderException]
  override def render: String = try renderImpl
  catch {
    case e: EndOfStreamException =>
      throw RenderException(cause = e)
  }

  private def renderImpl = renderables
    .stream
    .mapE1(_.render)
    .fold(_ + _)
    .orElse("")
}