package com.meti;

import static com.meti.BooleanAttribute.FalseAttribute;
import static com.meti.BooleanAttribute.TrueAttribute;

public abstract class IntegerType implements Token {
	public static Token unsigned(int bits) {
		return new Unsigned(bits);
	}

	public static Token signed(int bits) {
		return new Signed(bits);
	}

	@Override
	public Attribute apply(Attribute.Name name) throws AttributeException {
		return switch (name) {
			case Type -> Type.Integer;
			case Bits -> computeBits();
			case Sign -> computeSign();
			default -> throw new AttributeException();
		};
	}

	protected abstract Attribute computeBits();

	protected abstract Attribute computeSign();

	private static class Unsigned extends IntegerType {
		private final int bits;

		public Unsigned(int bits) {
			this.bits = bits;
		}

		@Override
		protected Attribute computeBits() {
			return new IntegerAttribute(bits);
		}

		@Override
		protected Attribute computeSign() {
			return FalseAttribute;
		}
	}

	private static class Signed extends IntegerType {
		private final int bits;

		public Signed(int bits) {
			this.bits = bits;
		}

		@Override
		protected Attribute computeBits() {
			return new IntegerAttribute(bits);
		}

		@Override
		protected Attribute computeSign() {
			return TrueAttribute;
		}
	}
}
