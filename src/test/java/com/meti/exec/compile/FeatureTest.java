package com.meti.exec.compile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FeatureTest extends Feature {

	@Test
	void spaces() {
		assertEquals("1 2 3 4", formatTarget("   1  2  3    4"));
	}

	@Test
	void tabs() {
		assertEquals("", formatTarget("\t"));
	}

	@Test
	void newLines() {
		assertEquals("", formatTarget("\n"));
	}

	@Test
	void normal() {
		assertEquals(formatTarget("""
				struct Wrapper[T] {
					const value : T
				}
				"""), formatTarget("""
				struct Wrapper[T] {
					const value : T
				}
				"""));
	}
}