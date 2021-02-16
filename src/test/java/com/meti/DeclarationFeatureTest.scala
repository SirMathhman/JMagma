package com.meti

import com.meti.MagmaAssertions.assertCompile
import org.junit.jupiter.api.Test

class DeclarationFeatureTest {
	@Test
	def with_both(): Unit =
		assertCompile("int x=10", "const x : I16 = 10;")
}
