package com.meti.compile;

import com.meti.api.core.Option;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.meti.api.core.Some.Some;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CompilerTest {
	private static final MagmaCompiler Compiler = MagmaCompiler.MagmaCompiler_;

	@Test
	void compileSimple() throws IOException, CompileException {
		Compiler.compile(new StringSource("10"), (script, value) -> {
			assertEquals("10", value.renderToString(MagmaCompiler.CTargetType.Source));
			assertEquals("", value.renderToString(MagmaCompiler.CTargetType.Header));
			return Collections.emptyList();
		});
	}

	@Test
	void helloWorld() {
		assertSource("#include <stdio.h>\nint main(){printf(\"Hello World!\");return 0;}", """
				import native stdio;
				native def printf(format : Ref[I8], args : Any...) : Void;
				def main() : I16 => {
					printf("Hello World!");
					return 0;
				}
				""", "");
	}

	@Test
	void order() {
		assertSource("struct Wrapper{}void test(){}", "def test() : Void => {}struct Wrapper{}", "");
	}

	@Test
	void multipleLines() {
		assertSource("int x=10;int y=20;", """
				const x : I16 = 10;
				const y : I16 = 20;
				""", "");
	}

	@Test
	void nativeFunctions() {
		assertSource("", "native def test() : Void", "");
	}

	@Test
	void nativeImports() {
		assertSource("#include <stdio.h>\n", "import native stdio", "");
	}

	@Test
	void invocations() {
		assertSource("myFunction(10,20)", "myFunction(10, 20)", "");
	}

	@Test
	void structure() {
		assertSource("struct Wrapper{int value;}", "struct Wrapper {const value : I16}", "");
	}

	@Test
	void emptyStructure() {
		assertSource("struct Empty{}", "struct Empty{}", "");
	}

	private void assertSource(String input, String target, String header) {
		try {
			Compiler.compile(new StringSource(input), (script, value) -> {
				assertEquals(target, value.renderToString(MagmaCompiler.CTargetType.Source));
				assertEquals(header, value.renderToString(MagmaCompiler.CTargetType.Header));
				return Collections.emptyList();
			});
		} catch (CompileException | IOException e) {
			fail(e);
		}
	}

	@Test
	void testTrue() {
		assertSource("1", "true", "");
	}

	@Test
	void testFalse() {
		assertSource("0", "false", "");
	}

	@Test
	void testIf() {
		assertSource("if(1){}", "if(true){}", "");
	}

	@Test
	void testWhile() {
		assertSource("while(0){}", "while(false){}", "");
	}

	@Test
	void compileDeclarations() {
		assertSource("int x=10;", "const x : I16 = 10", "");
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
		assertSource("return 10;", "return 10", "");
	}

	@Test
	void compileMain() {
		assertSource("int main(){return 0;}", "def main() : I16 => {return 0;}", "");
	}

	@Test
	void compileInt() {
		assertSource("5", "5", "");
	}

	private static record StringSource(String s) implements Source {
		@Override
		public Option<String> read(Script script) throws IOException {
			return Some(s);
		}

		@Override
		public List<Script> list() throws IOException {
			var script = new ListScript(Collections.emptyList(), "Main");
			return List.of(script);
		}
	}
}