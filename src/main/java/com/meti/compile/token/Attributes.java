package com.meti.compile.token;

import com.meti.api.magma.collect.ArrayLists;
import com.meti.api.magma.collect.CollectionException;
import com.meti.api.magma.collect.List;
import com.meti.api.magma.collect.Sequence;

public class Attributes {
	public static final Attribute.Builder<Field> EmptyFields = new FieldList.Builder(ArrayLists.empty());

	public static final record FieldList(List<Field> fields) implements Attribute {
		@Override
		public Sequence<Field> asFieldList() {
			return fields;
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
