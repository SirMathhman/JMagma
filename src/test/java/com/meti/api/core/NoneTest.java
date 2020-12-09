package com.meti.api.core;

import com.meti.api.collect.IndexException;
import org.junit.jupiter.api.Test;

import static com.meti.api.collect.IndexException.IndexException;
import static com.meti.api.core.None.None;
import static org.junit.jupiter.api.Assertions.*;

class NoneTest {

	@Test
	void ifPresentOrElse() {
		assertFalse(None().ifPresentOrElse(
				o -> {
				},
				() -> {
				}
		));
	}

	@Test
	void map() {
		assertFalse(None().map(value -> value).isPresent());
	}

	@Test
	void filter() {
		assertFalse(None().filter(value -> false).isPresent());
	}

	@Test
	void orElseThrow() {
		assertThrows(IndexException.class, () -> None().orElseThrow(() -> IndexException("")));
	}

	@Test
	void orElse() {
		assertEquals("test", None().orElse("test"));
	}

	@Test
	void isPresent() {
		assertFalse(None().isPresent());
	}

	@Test
	void isEmpty() {
		assertTrue(None().isEmpty());
	}
}