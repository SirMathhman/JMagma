package com.meti.compile.feature.content;

import com.meti.compile.feature.Type;

import java.util.Objects;
import java.util.Optional;

public class ContentType implements Type {
	private final String content;

	private ContentType(String content) {
		this.content = content;
	}

	public static ContentType ContentType(String content) {
		return new ContentType(content);
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
	public String toString() {
		return "ContentType{" +
		       "findContent='" + content + '\'' +
		       '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ContentType that = (ContentType) o;
		return Objects.equals(content, that.content);
	}

	@Override
	public int hashCode() {
		return Objects.hash(content);
	}

	@Override
	public String render(String value) {
		return content + " " + value;
	}
}
