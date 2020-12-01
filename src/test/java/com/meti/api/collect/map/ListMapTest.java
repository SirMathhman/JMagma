package com.meti.api.collect.map;

import com.meti.api.collect.map.ListMap;
import org.junit.jupiter.api.Test;

import static com.meti.api.collect.list.ArrayList.ArrayList;
import static com.meti.api.collect.map.ListMap.Binding;
import static com.meti.api.collect.map.ListMap.ListMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListMapTest {

	@Test
	void containsKey() {
		assertTrue(createMap().containsKey("test0"));
	}

	private ListMap<String, String> createMap() {
		return new ListMap<>(ArrayList(Binding("test0", "test1")));
	}

	@Test
	void get() {
		assertEquals("test1", createMap().get("test0").orElse("other"));
	}

	@Test
	void put() {
		assertEquals("test3", createMap()
				.put("test2", "test3")
				.get("test2")
				.orElse("other"));
	}

	@Test
	void ensureExtant() {
		assertEquals("test1", createMap()
				.ensure("test0", "test2")
				.get("test0")
				.orElse("other"));
	}

	@Test
	void ensureExtinct() {
		assertEquals("test2", ListMap()
				.ensure("test0", "test2")
				.get("test0")
				.orElse("other"));
	}

	@Test
	void putAll() {
		var first = ListMap().put("test0", "test1");
		var second = ListMap().put("test2", "test3");
		var actual = first.putAll(second);
		var expected = ListMap()
				.put("test0", "test1")
				.put("test2", "test3");
		assertEquals(actual, expected);
	}

	@Test
	void testEquals() {
		assertEquals(createMap(), createMap());
	}

	@Test
	void orderedKeys() {
		var map = ListMap()
				.put("test0", "test1")
				.put("test2", "test3");
		assertEquals(ArrayList("test0", "test2"), map.orderedKeys());
	}
}