package com.meti.compile;

import com.meti.api.io.File;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;

import static com.meti.compile.MagmaCompiler.MagmaCompiler_;
import static org.junit.jupiter.api.Assertions.*;

class CompilerTest {
	@Test
	void compileSimple() throws IOException, CompileException {
		Target<CClass, File> target = (script, value) -> {
			assertEquals("10", value.render(CClass.Source));
			assertEquals("", value.render(CClass.Header));
			return Collections.emptyList();
		};
		assertTrue(MagmaCompiler_.compile(new StringSource("10"), target).isEmpty());
	}

	@Test
	void helloWorld() {
		assertSource("""
				import native stdio;
				native def printf(format : Ref[I8], args : Any...) : Void;
				def main() : I16 => {
					printf("Hello World!");
					return 0;
				}
				""",
				"#include \"Main.h\"\nint main(){printf(\"Hello World!\");return 0;}",
				"#include <stdio.h>\n");
	}

	@Test
	void order() {
		assertSource("def test() : Void => {}struct Wrapper{}", "struct Wrapper{}void test(){}", "");
	}

	@Test
	void multipleLines() {
		assertSource("""
				const x : I16 = 10;
				const y : I16 = 20;
				""", "int x=10;int y=20;", "");
	}

	@Test
	void nativeFunctions() {
		assertSource("native def test() : Void", "", "");
	}

	@Test
	void nativeImports() {
		assertSource("import native stdio", "#include \"Main.h\"\n", "#include <stdio.h>\n");
	}

	@Test
	void invocations() {
		assertSource("myFunction(10, 20)", "myFunction(10,20)", "");
	}

	@Test
	void structure() {
		assertSource("struct Wrapper {const value : I16}", "struct Wrapper{int value;}", "");
	}

	@Test
	void emptyStructure() {
		assertSource("struct Empty{}", "struct Empty{}", "");
	}

	private void assertSource(String input, String target, String header) {
		try {
			MagmaCompiler_.compile(new StringSource(input), (script, value) -> {
				assertEquals(target, value.render(CClass.Source));
				assertEquals(header, value.render(CClass.Header));
				return Collections.emptyList();
			});
		} catch (CompileException | IOException e) {
			fail(e);
		}
	}

	@Test
	void testTrue() {
		assertSource("true", "1", "");
	}

	@Test
	void testFalse() {
		assertSource("false", "0", "");
	}

	@Test
	void testIf() {
		assertSource("if(true){}", "if(1){}", "");
	}

	@Test
	void testWhile() {
		assertSource("while(false){}", "while(0){}", "");
	}

	@Test
	void compileDeclarations() {
		assertSource("const x : I16 = 10", "int x=10;", "");
	}

	@Test
	void compileBlocks() {
		assertSource("{{}{}}", "{{}{}}", "");
	}

	@Test
	void blockChildren() {
		assertSource("{return 0;}", "{return 0;}", "");
	}

	@Test
	void compileReturn() {
		assertSource("return 10", "return 10;", "");
	}

	@Test
	void compileMain() {
		assertSource("def main() : I16 => {return 0;}", "int main(){return 0;}", "");
	}

	@Test
	void compileInt() {
		assertSource("5", "5", "");
	}

}