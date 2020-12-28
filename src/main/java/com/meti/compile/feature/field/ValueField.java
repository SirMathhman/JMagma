package com.meti.compile.feature.field;

import com.meti.api.core.EF1;
import com.meti.api.core.F1;
import com.meti.api.core.Option;
import com.meti.api.core.Some;
import com.meti.compile.token.Node;
import com.meti.compile.token.Type;

import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import static com.meti.api.core.Some.Some;

public record ValueField(Set<Flag> flags, String name, Type type, Node value) implements Field {
	@Override
	public String toString() {
		return "ValueField{" +
		       "name='" + name + '\'' +
		       ", type=" + type +
		       ", flags=" + flags +
		       ", value=" + value +
		       '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ValueField that = (ValueField) o;
		return Objects.equals(name, that.name) &&
		       Objects.equals(type, that.type) &&
		       Objects.equals(flags, that.flags) &&
		       Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, type, flags, value);
	}

	@Override
	public String render() {
		return type.render(name) + "=" + value.render();
	}

	@Override
	public <E extends Exception> Field mapByType(EF1<Type, Type, E> mapper) throws E {
		return new ValueField(flags, name, mapper.apply(type), value);
	}

	@Override
	public <T> T applyToType(Function<Type, T> mapper) {
		return mapper.apply(type);
	}

	@Override
	public <T> T applyToName(Function<String, T> mapper) {
		return mapper.apply(name);
	}


	@Override
	public boolean isFlagged(Flag flag) {
		return flags.contains(flag);
	}

	@Override
	public Field replaceType(Type replacement) {
		return new ValueField(flags, name, replacement, value);
	}

	@Override
	public Option<Node> findValue() {
		return Some(value);
	}

	@Override
	public Field mapByValue(F1<Node, Node> mapper) {
		return new ValueField(flags, name, type, mapper.apply(value));
	}

	@Override
	public <E extends Exception> Field mapByValueExceptionally(EF1<Node, Node, E> mapper) throws E {
		return new ValueField(flags, name, type, mapper.apply(value));
	}

	@Override
	public Option<Type> findType() {
		return Some(type);
	}
}
