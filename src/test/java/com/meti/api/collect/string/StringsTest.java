package com.meti.api.collect.string;

import com.meti.api.core.FormatException;
import org.junit.jupiter.api.Test;

import static com.meti.api.collect.string.Strings.asInt;
import static com.meti.api.collect.string.Strings.valueOfInt;
import static org.junit.jupiter.api.Assertions.*;

class StringsTest {
	@Test
	void asIntBlank(){
		assertThrows(FormatException.class, () -> asInt(""));
	}

	@Test
	void asIntPositive() throws FormatException {
		assertEquals(10, asInt("10"));
	}

	@Test
	void asIntNegative() throws FormatException {
		assertEquals(-10, asInt("-10"));
	}

	@Test
	void asIntInvalid(){
		assertThrows(FormatException.class, () -> asInt(" 2 3"));
	}

	@Test
	void valueOfIntNegative() {
		assertEquals("-55", valueOfInt(-55));
	}

	@Test
	void valueOfIntZero() {
		assertEquals("0", valueOfInt(0));
	}

	@Test
	void valueOfIntPositive() {
		assertEquals("343", valueOfInt(343));
	}

	@Test
	void concat() {
		assertEquals("xy", Strings.concat("x", "y"));
	}

	@Test
	void compareToEquals() {
		assertEquals(0, Strings.compareTo("x", "x"));
	}

	@Test
	void compareToLess() {
		assertEquals(-3, Strings.compareTo("a", "d"));
	}

	@Test
	void compareToGreater() {
		assertEquals(3, Strings.compareTo("d", "a"));
	}

	@Test
	void slice(){
		assertEquals("1", Strings.slice("10", 0, 1));
	}

	@Test
	void length() {
		assertEquals(4, Strings.length("test"));
	}

	@Test
	void testEquals() {
		assertTrue(Strings.equalsTo("test", "test"));
	}
}