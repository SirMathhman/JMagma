package com.meti.api.collect.map;

import org.junit.jupiter.api.Test;

import static com.meti.api.collect.map.EmptyMap.EmptyMap;
import static org.junit.jupiter.api.Assertions.*;

class EmptyMapTest {

	@Test
	void get() {
		assertTrue(EmptyMap().get("test").isEmpty());
	}

	@Test
	void orderedKeys() {
		assertTrue(EmptyMap().orderedKeys().isEmpty());
	}
}