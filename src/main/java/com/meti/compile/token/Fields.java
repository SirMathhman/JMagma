package com.meti.compile.token;

import com.meti.api.magma.collect.*;

public class Fields {
	public static final Neither Empty = new Neither(ArrayLists.empty());

	public Fields() {
	}

	private static final record Empty(Sequence<Flag> flags, String name, Token type) implements Field {
		@Override
		public String findName() {
			return name;
		}

		@Override
		public Token findType() {
			return type;
		}

		@Override
		public Field withType(Token type) {
			try {
				return Sequences.stream(flags)
						.fold(Empty, Fields.Neither::withFlag)
						.withName(name)
						.withType(type)
						.complete();
			} catch (StreamException e) {
				return this;
			}
		}
	}

	public static record Neither(List<Field.Flag> flags) {
		public Neither withFlag(Field.Flag flag) throws CollectionException {
			return new Neither(flags.add(flag));
		}

		public WithName withName(String name) {
			return new WithName(flags, name);
		}
	}

	public record Both(List<Field.Flag> flags, String name, Token type) {
		public Field complete() {
			return new Empty(flags, name, type);
		}
	}

	public record WithName(List<Field.Flag> flags, String name) {
		public Both withType(Token type) {
			return new Both(flags, name, type);
		}
	}
}
