package com.meti.compile.feature.content;

import com.meti.api.core.None;
import com.meti.api.core.Option;
import com.meti.api.core.Some;
import com.meti.compile.feature.field.Field;
import com.meti.compile.token.Node;

import java.util.Objects;
import java.util.Optional;

public class ContentNode implements Node {
	private final String content;

	private ContentNode(String content) {
		this.content = content;
	}

	public static ContentNode ContentNode(String content) {
		return new ContentNode(content);
	}

	@Override
	public String toString() {
		return "ContentNode{" +
		       "content='" + content + '\'' +
		       '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ContentNode that = (ContentNode) o;
		return Objects.equals(content, that.content);
	}

	@Override
	public int hashCode() {
		return Objects.hash(content);
	}

	@Override
	public String render() {
		return content;
	}

	@Override
	public Optional<String> findContent() {
		return Optional.of(content);
	}

	@Override
	public boolean is(Group group) {
		return group == Group.Content;
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
