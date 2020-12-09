package com.meti.exec.compile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeatureTest extends Feature{

	@Test
	void spaces() {
		assertEquals("1 2 3 4", formatTarget("   1  2  3    4"));
	}
}