package com.meti.compile.feature.struct;

import com.meti.api.core.EF1;
import com.meti.compile.feature.Node;
import com.meti.compile.feature.field.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record Structure(String name, List<Field> fields) implements Node {
	static Incomplete Structure() {
		return new Incomplete(Collections.emptyList());
	}

	@Override
	public boolean is(Group group) {
		return group == Group.Structure;
	}

	@Override
	public String toString() {
		return "Structure{" +
		       "name='" + name + '\'' +
		       ", fields=" + fields +
		       '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Structure structure = (Structure) o;
		return Objects.equals(name, structure.name) &&
		       Objects.equals(fields, structure.fields);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, fields);
	}

	@Override
	public <E extends Exception> Node mapByFields(EF1<Field, Field, E> mapper) throws E {
		var newFields = new ArrayList<Field>();
		for (Field field : fields) {
			newFields.add(mapper.apply(field));
		}
		return new Structure(name, newFields);
	}

	@Override
	public String render() {
		return "struct " + name + renderFields();
	}

	private String renderFields() {
		return fields.stream()
				.map(Field::render)
				.map(value -> value + ";")
				.collect(Collectors.joining("", "{", "}"));
	}

	public static record Incomplete(List<Field> fields) {
		Incomplete withField(Field field) {
			var newList = new ArrayList<>(fields);
			newList.add(field);
			return new Incomplete(newList);
		}

		Complete withName(String name) {
			return new Complete(name, fields);
		}
	}

	public static record Complete(String name, List<Field> fields) {
		Node complete() {
			return new Structure(name, fields);
		}

		public Complete withField(Field field) {
			var newList = new ArrayList<>(fields);
			newList.add(field);
			return new Complete(name, newList);
		}
	}
}
