package com.meti.compile.feature.content;

import com.meti.compile.token.LeafNode;

import java.util.Objects;
import java.util.Optional;

public class ContentNode implements LeafNode {
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
}
