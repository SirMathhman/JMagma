package com.meti.compile.feature.condition;

import com.meti.api.core.EF1;
import com.meti.api.core.None;
import com.meti.api.core.Option;
import com.meti.api.core.Some;
import com.meti.compile.feature.field.Field;
import com.meti.compile.token.Node;

import java.util.Objects;
import java.util.Optional;

public class Conditional implements Node {
	private final Node condition;
	private final Node block;
	private final Type type;

	private Conditional(Node condition, Node block, Type type) {
		this.condition = condition;
		this.block = block;
		this.type = type;
	}

	public static Node If(Node condition, Node block) {
		return Conditional(condition, block, Type.IF);
	}

	public static Node While(Node condition, Node block) {
		return Conditional(condition, block, Type.WHILE);
	}

	static Conditional Conditional(Node condition, Node block, Type type) {
		return new Conditional(condition, block, type);
	}

	@Override
	public String toString() {
		return "Conditional{" +
		       "condition=" + condition +
		       ", block=" + block +
		       ", type=" + type +
		       '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Conditional that = (Conditional) o;
		return Objects.equals(condition, that.condition) &&
		       Objects.equals(block, that.block) &&
		       type == that.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(condition, block, type);
	}

	@Override
	public <E extends Exception> Node mapByChildrenExceptionally(EF1<Node, Node, E> mapper) throws E {
		return Conditional(mapper.apply(condition), mapper.apply(block), type);
	}

	@Override
	public String render() {
		return type.name().toLowerCase() + "(" + condition.render() + ")" + block.render();
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

	enum Type {
		WHILE,
		IF
	}
}
