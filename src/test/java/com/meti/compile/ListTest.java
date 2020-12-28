package com.meti.compile;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ListTest {
	@Test
	void testEquals() {
		var first = new ArrayList<>(List.of("com", "com/meti"));
		var second = new LinkedList<>(List.of("com", "com/meti"));
		assertEquals(first, second);
	}

	@Test
	void testNotEquals() {
		var first = new ArrayList<>(List.of("com/meti", "com"));
		var second = new LinkedList<>(List.of("com", "com/meti"));
		assertNotEquals(first, second);
	}
}
