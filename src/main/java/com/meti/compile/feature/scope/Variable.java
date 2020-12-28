package com.meti.compile.feature.scope;

import com.meti.api.core.None;
import com.meti.api.core.Option;
import com.meti.api.core.Some;
import com.meti.compile.feature.field.Field;
import com.meti.compile.token.Node;

import java.util.Objects;
import java.util.Optional;

public class Variable implements Node {
	private final String content;

	private Variable(String content) {
		this.content = content;
	}

	public static Variable Variable(String content) {
		return new Variable(content);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Variable variable = (Variable) o;
		return Objects.equals(content, variable.content);
	}

	@Override
	public int hashCode() {
		return Objects.hash(content);
	}

	@Override
	public Optional<String> findContent() {
		return Optional.of(content);
	}

	@Override
	public String render() {
		return content;
	}

	@Override
	public Option<Field> findIdentity() {
		return findIdentity2()
				.map(Some::Some)
				.orElseGet(None::None);
	}

	@Deprecated
	private Optional<Field> findIdentity2() {
		return Optional.empty();
	}
}
