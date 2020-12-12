package com.meti.api.core;

import com.meti.api.collect.string.Strings;
import org.junit.jupiter.api.Test;

import static com.meti.api.core.Some.Some;
import static org.junit.jupiter.api.Assertions.*;

class SomeTest {

	@Test
	void ifPresentOrElse() {
		assertTrue(Some("test").ifPresentOrElse(
				o -> {
				},
				() -> {
				}
		));
	}

	@Test
	void map() {
		assertEquals(4, Some("test")
				.mapExceptionally(Strings::length)
				.orElse(-1));
	}

	@Test
	void filter() {
		assertTrue(Some("test")
				.filter(value -> Strings.equals(value, "test1"))
				.isEmpty());
	}

	@Test
	void orElseThrow() {
		assertDoesNotThrow(() -> Some("test").orElseThrow(Exception::new));
	}

	@Test
	void orElse() {
		assertEquals("test", Some("test").orElse("test1"));
	}

	@Test
	void isPresent() {
		assertTrue(Some("test").isPresent());
	}

	@Test
	void isEmpty() {
		assertFalse(Some("test").isEmpty());
	}
}