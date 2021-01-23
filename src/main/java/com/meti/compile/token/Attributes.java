package com.meti.compile.token;

import com.meti.api.magma.collect.*;

public class Attributes {
	public static final Attribute.Builder<Field> EmptyFields = new FieldList.Builder(ArrayLists.empty());

	public static final record FieldList(List<Field> fields) implements Attribute {
		private Sequence<Field> asFieldSequence() {
			return fields;
		}

		@Deprecated
		private Sequence<Token> asTokenSequence() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Stream<Field> streamFields() {
			return Sequences.stream(asFieldSequence());
		}

		@Override
		public Stream<Token> streamTokens(){
			return Sequences.stream(asTokenSequence());
		}

		public static record Builder(List<Field> list) implements Attribute.Builder<Field> {
			@Override
			public Attribute.Builder<Field> add(Field field) throws CollectionException {
				return new Builder(list.add(field));
			}

			@Override
			public Attribute complete() {
				return new FieldList(list);
			}
		}
	}
}
