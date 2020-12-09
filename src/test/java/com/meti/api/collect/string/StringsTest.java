package com.meti.api.collect.string;

import com.meti.api.collect.stream.StreamException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringsTest {

	@Test
	void concat() {
		assertEquals("xy", Strings.concat("x", "y"));
	}

	@Test
	void compareToEquals() {
		assertEquals(0, Strings.compareTo("x", "x"));
	}

	@Test
	void compareToLess() {
		assertEquals(-3, Strings.compareTo("a", "d"));
	}

	@Test
	void compareToGreater() {
		assertEquals(3, Strings.compareTo("d", "a"));
	}

	@Test
	void stream() throws StreamException {
		assertEquals('t', Strings.stream("test")
				.head()
				.orElse('\0'));
	}

	@Test
	void length() {
		assertEquals(4, Strings.length("test"));
	}

	@Test
	void testEquals() {
		assertTrue(Strings.equals("test", "test"));
	}
}