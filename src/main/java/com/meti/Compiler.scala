package com.meti

object Compiler {
  @throws[CompileException]
  def compile(input: Input): Renderable = {
    if (input.isEmpty) return new EmptyOutput
    val root = lex(input)
    render(root)
  }

  @throws[StreamException]
  private def isInteger(input: Input) = input.stream.allMatch(_.isDigit)

  @throws[CompileException]
  private def lex(input: Input): Token = try 
    if (input.test) new Return(lex(input.slice(7)))
    else if (isInteger(input)) new Integer(input)
    else (name: Attribute.Name) => new InputAttribute(new Input("int main(){return 0;}"))
    catch {
      case e: StreamException =>
        throw new CompileException(e)
    }

  @throws[RenderException]
  private def render(root: Token): Output = {
    try if (root.apply(Attribute.Name.Group) eq Token.Type.Return) {
      val root1 = root.apply(Attribute.Name.Value).computeToken
      return new StringOutput("return ").asInstanceOf[Output].concat(render(root1)).concat(";")
    }
    catch {
      case e: AttributeException =>
        throw new RenderException("Failed to render return statement.", e)
    }
    try root.apply(Attribute.Name.Content).computeInput.asOutput
    catch {
      case e: AttributeException =>
        throw new RenderException("Unable to render token because no input was present.")
    }
  }
}