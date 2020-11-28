package com.meti.api.collect;

import com.meti.MutableList;
import org.junit.jupiter.api.Test;

import static com.meti.api.collect.ArrayList.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {
	@Test
	void addAllSize(){
		assertEquals(2, createList().addAll(createList()).size());
	}

	@Test
	void addAllOld() throws IndexException {
		assertEquals("test0", createList().addAll(createList()).get(0));
	}

	@Test
	void addAllNew() throws IndexException {
		assertEquals("test0", createList().addAll(createList()).get(0));
	}

	@Test
	void empty() {
		assertEquals(0, createList().empty().size());
	}

	@Test
	void size() {
		assertEquals(1, createList().size());
	}

	@Test
	void getInBounds() throws IndexException {
		assertEquals("test0", createList().get(0));
	}

	@Test
	void getNegative() {
		assertThrows(IndexException.class, () -> createList().get(-1));
	}

	@Test
	void getPositive() {
		assertThrows(IndexException.class, () -> createList().get(1));
	}

	private MutableList<Object> createList() {
		var array = new Object[]{"test0"};
		var capacity = 1;
		var size = 1;
		return ArrayList(array, capacity, size);
	}

	@Test
	void setInBounds() throws IndexException {
		assertEquals("test1", createList().set(0, "test1").get(0));
	}

	@Test
	void setPadding() throws IndexException {
		assertEquals("test1", createList().set(3, "test1").get(3));
	}

	@Test
	void addSize() {
		assertEquals(2, createList().add("test1").size());
	}

	@Test
	void addPrevious() throws IndexException {
		assertEquals("test0", createList().add("test1").get(0));
	}

	@Test
	void addNew() throws IndexException {
		assertEquals("test1", createList().add("test1").get(1));
	}

	@Test
	void isEmpty() {
		assertFalse(createList().isEmpty());
	}
}