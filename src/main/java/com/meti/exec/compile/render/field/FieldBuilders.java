package com.meti.exec.compile.render.field;

import com.meti.api.collect.Set;
import com.meti.api.collect.list.ArrayList;
import com.meti.api.collect.list.List;
import com.meti.exec.compile.render.Node;
import com.meti.exec.compile.render.Type;
import com.meti.exec.compile.render.field.Field.Flag;

public class FieldBuilders {
	private FieldBuilders() {
	}

	static Empty FieldBuilder() {
		return new Empty(ArrayList.empty(Flag::equals));
	}

	static record All(List<Flag> flags, String name, Type type, Node value) {
		Field complete() {
			return new FieldWithValue(flags, name, type, value);
		}
	}

	static record WithoutValue(List<Flag> flags, String name, Type type) {
		public All withValue(Node value) {
			return new All(flags, name, type, value);
		}

		public Field complete() {
			return new FieldWithoutValue(flags, name, type);
		}
	}

	static record WithName(List<Flag> flags, String name) {
		public WithoutValue withType(Type type) {
			return new WithoutValue(flags, name, type);
		}
	}

	static record Empty(List<Flag> flags) {
		Empty withFlag(Flag flag) {
			return new Empty(flags.add(flag));
		}

		public WithName withName(String name) {
			return new WithName(flags, name);
		}
	}
}
