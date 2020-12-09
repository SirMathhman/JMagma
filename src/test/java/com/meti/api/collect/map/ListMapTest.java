package com.meti.api.collect.map;

import com.meti.api.collect.string.Strings;
import org.junit.jupiter.api.Test;

import static com.meti.api.collect.map.ListMap.ListMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListMapTest {

	@Test
	void size() {
		assertEquals(1, ListMap(Strings::compareTo, "test0", "test1").size());
	}

	@Test
	void containsKey() {
		assertTrue(ListMap(Strings::compareTo, "test0", "test1").containsKey("test0"));
	}

	@Test
	void remove() {
		assertEquals(0, ListMap(Strings::compareTo, "test0", "test1").remove("test0").size());
	}

	@Test
	void put() {
		assertTrue(ListMap(Strings::compareTo).put("test0", "test1").containsKey("test0"));
	}

	@Test
	void get() {
		assertEquals("test1", ListMap(Strings::compareTo, "test0", "test1").get("test0").orElse(""));
	}
}