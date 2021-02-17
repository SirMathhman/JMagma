package com.meti.compile.integer;

import com.meti.compile.token.Token;
import com.meti.compile.attribute.Attribute;
import com.meti.compile.attribute.AttributeException;
import com.meti.compile.attribute.IntegerAttribute;

import java.util.Objects;

import static com.meti.compile.attribute.BooleanAttribute.FalseAttribute;
import static com.meti.compile.attribute.BooleanAttribute.TrueAttribute;

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

		@Override
		public int hashCode() {
			return Objects.hash(bits);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Unsigned unsigned = (Unsigned) o;
			return bits == unsigned.bits;
		}

		@Override
		public String toString() {
			return "{\"signed\":false,\"bits\":%d}".formatted(bits);
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

		@Override
		public int hashCode() {
			return Objects.hash(bits);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Signed signed = (Signed) o;
			return bits == signed.bits;
		}

		@Override
		public String toString() {
			return "{\"signed\":true,\"bits\":%d}".formatted(bits);
		}
	}
}
