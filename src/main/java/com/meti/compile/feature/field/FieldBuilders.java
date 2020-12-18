package com.meti.compile.feature.field;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.Type;
import com.meti.compile.feature.field.Field.Flag;

import java.util.HashSet;
import java.util.Set;

import static com.meti.compile.feature.field.EmptyField.EmptyField;
import static com.meti.compile.feature.field.ValueField.ValueField;

public class FieldBuilders {
	public FieldBuilders() {
	}

	public static None FieldBuilder() {
		return new None(new HashSet<>());
	}

	public static record None(Set<Flag> flags) {
		public None withFlag(Flag flag) {
			Set<Flag> newFlags = new HashSet<>(flags);
			newFlags.add(flag);
			return new None(newFlags);
		}

		public WithName withName(String name) {
			return new WithName(flags, name);
		}

		public None withFlags(Set<Flag> flags) {
			Set<Flag> newFlags = new HashSet<>(flags);
			newFlags.addAll(flags);
			return new None(newFlags);
		}
	}

	public static record WithName(Set<Flag> flags, String name) {
		public WithoutValue withType(Type type) {
			return new WithoutValue(flags, name, type);
		}
	}

	public static record WithoutValue(Set<Flag> flags, String name, Type type) {
		public Field complete() {
			return EmptyField(flags, name, type);
		}

		public All withValue(Node value) {
			return new All(flags, name, type, value);
		}
	}

	public static record All(Set<Flag> flags, String name, Type type, Node value) {
		public Field complete() {
			return ValueField(flags, name, type, value);
		}
	}
}
