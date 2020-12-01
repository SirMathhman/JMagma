package com.meti.api.collect.map;

import com.meti.api.collect.map.SingletonMap;
import org.junit.jupiter.api.Test;

import static com.meti.api.collect.list.SingletonList.SingletonList;
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
		return SingletonMap.SingletonMap("key", "value");
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