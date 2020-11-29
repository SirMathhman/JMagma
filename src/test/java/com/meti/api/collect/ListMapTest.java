package com.meti.api.collect;

import com.meti.MutableMap;
import org.junit.jupiter.api.Test;

import static com.meti.api.collect.ArrayList.ArrayList;
import static com.meti.api.collect.ListMap.Binding;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListMapTest {

	@Test
	void containsKey() {
		assertTrue(createMap().containsKey("key"));
	}

	private MutableMap<String, String> createMap() {
		var list = ArrayList(Binding("key", "value"));
		return new ListMap<>(list);
	}

	@Test
	void get() {
		assertEquals("value", createMap().get("key").orElse("other"));
	}

	@Test
	void put() {
		assertEquals("value2", createMap().put("key2", "value2").get("key2").orElse("other"));
	}

	@Test
	void ensure() {
	}

	@Test
	void testEnsure() {
	}

	@Test
	void putAll() {
	}

	@Test
	void orderedKeys() {
	}
}