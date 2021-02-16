package com.meti

object Attribute {

	sealed trait Name extends Attribute

	object Name {

		case object Group extends Name

		case object Content extends Name

		case object Value extends Name

	}

}

trait Attribute {
	@throws[AttributeException]
	def computeInput: Input = throw AttributeException("Not input.")

	@throws[AttributeException]
	def computeToken: Token = throw AttributeException("Not a token.")
}