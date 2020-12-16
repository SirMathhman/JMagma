package com.meti.api.collect.string;

import com.meti.api.core.FormatException;
import org.junit.jupiter.api.Test;

import static com.meti.api.collect.string.Strings.*;
import static org.junit.jupiter.api.Assertions.*;

class StringsTest {
	@Test
	void toUpperCase(){
		assertEquals("DUMMY", Strings.toUpperCase("dUmmY"));
	}

	@Test
	void firstPresent(){
		assertEquals(1, firstChar("test", c -> c == 'e').orElse(-1));
	}

	@Test
	void firstEmpty(){
		assertTrue(firstChar("test", c -> c == 'c').isEmpty());
	}

	@Test
	void asIntBlank() {
		assertThrows(FormatException.class, () -> toInt(""));
	}

	@Test
	void asIntPositive() throws FormatException {
		assertEquals(10, toInt("10"));
	}

	@Test
	void asIntNegative() throws FormatException {
		assertEquals(-10, toInt("-10"));
	}

	@Test
	void asIntInvalid() {
		assertThrows(FormatException.class, () -> toInt(" 2 3"));
	}

	@Test
	void valueOfIntNegative() {
		assertEquals("-55", fromInt(-55));
	}

	@Test
	void valueOfIntZero() {
		assertEquals("0", fromInt(0));
	}

	@Test
	void valueOfIntPositive() {
		assertEquals("343", fromInt(343));
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
	void slice() {
		assertEquals("1", Strings.slice("10", 0, 1));
	}

	@Test
	void firstString(){
		assertEquals(6, Strings.firstString("test1 %o", "%o").orElse(-1));
	}

	@Test
	void replaceFirst(){
		assertEquals("test1 test2", Strings.replaceFirst("test1 %o", "%o", "test2"));
	}

	@Test
	void format(){
		assertEquals("test1 test2", Strings.format("%o %o", "test1", "test2"));
	}

	@Test
	void length() {
		assertEquals(4, Strings.length("test"));
	}

	@Test
	void testEquals() {
		assertTrue(Strings.equalsTo("test", "test"));
	}

	@Test
	void trimFirst(){
		assertEquals("firstChar", Strings.trim(" firstChar"));
	}

	@Test
	void trimLast(){
		assertEquals("last", Strings.trim("last "));
	}

	@Test
	void trimNone(){
		assertEquals("test", Strings.trim("test"));
	}

	@Test
	void trimBoth() {
		assertEquals("const x", Strings.trim(" const x "));
	}
}