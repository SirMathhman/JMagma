package com.meti.api.collect;

import org.junit.jupiter.api.Test;

import static com.meti.api.collect.SingletonList.SingletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SingletonMapTest {
	@Test
	void getPresent() {
		assertTrue(createMap().get("key").isPresent());
	}

	@Test
	void getValue() {
		assertEquals("value", createMap().get("key").orElse("???"));
	}

	private SingletonMap<String, String> createMap() {
		return new SingletonMap<>("key", "value");
	}

	@Test
	void orderedKeys() {
		assertEquals(SingletonList("key"), createMap().orderedKeys());
	}

	@Test
	void containsKey() {
		assertTrue(createMap().containsKey("key"));
	}
}