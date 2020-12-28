package com.meti.compile.token;

import com.meti.api.core.None;
import com.meti.api.core.Option;
import com.meti.api.core.Some;
import com.meti.compile.feature.field.Field;

import java.util.Optional;

public class EmptyNode implements Node {
	public static final Node EmptyNode_ = new EmptyNode();

	private EmptyNode() {
	}

	@Override
	public String render() {
		return "";
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
