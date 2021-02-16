package com.meti

object ListOutput {
  def apply: Builder = new ListOutput.Builder()

  final class Builder(val renderables: List[Renderable] = ArrayList.empty) {
    def append(renderable: Renderable) = new Builder(renderables.add(renderable))

    def complete = new ListOutput(renderables)
  }
}

case class ListOutput(renderables: List[Renderable]) extends Output {
  override def concat(other: Renderable) = new ListOutput(renderables.add(other))

  override def concat(other: String) = new ListOutput(renderables.add(StringRenderable(other)))

  @throws[RenderException]
  override def render: String = try renderImpl
  catch {
    case e: EndOfStreamException =>
      throw new RenderException(e)
  }

  private def renderImpl = renderables
    .stream
    .mapE1(_.render)
    .fold(_ + _)
    .orElse("")
}