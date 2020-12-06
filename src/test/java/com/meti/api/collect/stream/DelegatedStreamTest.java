package com.meti.api.collect.stream;

import com.meti.api.collect.list.ArrayList;
import com.meti.api.collect.string.Strings;
import org.junit.jupiter.api.Test;

import static com.meti.api.collect.stream.SequenceStream.SequenceStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DelegatedStreamTest {

	@Test
	void map() throws StreamException {
		assertEquals("1, 2, 3, ", createStream()
				.map(value -> Strings.concat(value, ", "))
				.foldLeft("", (s, s2) -> s + s2));
	}

	@Test
	void head() throws StreamException {
		assertEquals("1", createStream()
				.head()
				.orElse(""));
	}

	private Stream<String> createStream() {
		return SequenceStream(ArrayList.of(Strings::compareTo, "1", "2", "3"));
	}

	@Test
	void anyMatch() throws StreamException {
		assertTrue(createStream().anyMatch(value -> Strings.compareTo(value, "1") == 0));
	}

	@Test
	void filter() throws StreamException {
		assertEquals("1", createStream()
				.filter(value -> Strings.compareTo(value, "1") == 0)
				.foldLeft("", (s, s2) -> s + s2));
	}

	@Test
	void foldLeft() throws StreamException {
		assertEquals("123", createStream().foldLeft("", (s, s2) -> s + s2));
	}
}