package com.meti.compile.feature.function;

import com.meti.compile.CompiledTest;
import org.junit.jupiter.api.Test;

class FunctionFeatureTest extends CompiledTest {
	@Test
	void testNativeFunctions() {
		assertSource("native def test() : Void", "", "");
	}

	@Test
	void testMain() {
		assertSource("def main() : I16 => {return 0;}", "int main(){return 0;}", "");
	}

	@Test
	void testImplicitFunction(){
		assertSource("def main() => {return 0;}", "int main(){return 0;}");
	}

	@Test
	void testImplicitProcedure(){
		assertSource("def main() => {}", "void main(){}");
	}
}
