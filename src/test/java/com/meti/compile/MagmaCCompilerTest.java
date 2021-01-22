package com.meti.compile;

import com.meti.compile.io.ListSource;
import com.meti.compile.io.MapLoader;
import com.meti.compile.io.Result;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.meti.compile.MagmaCCompiler.MagmaCCompiler_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MagmaCCompilerTest {

	@Test
	void compile() throws CompileException {
		var source = new ListSource(List.of("Main"));
		var loader = new MapLoader(Map.of(source, "def main() : I16 => {return 0;}"));
		var result = MagmaCCompiler_.compile(loader);
		var mappingOptional = result.apply(source)
				.map(Optional::of)
				.orElseGet(Optional::empty);
		Map<Result.Format, List<Token>> map = Map.of(
				CLang.Formats.Header, Collections.singletonList(new Content("")),
				CLang.Formats.Source, Collections.singletonList(new Content("int main(){return 0;}"))
		);
		var actual = mappingOptional.orElseThrow();
		assertEquals("""
				#ifndef Main
				#define Main
				#endif""", actual.apply(CLang.Formats.Header));
		assertEquals("""
				#include "Main.h"
				int main(){return 0;}""", actual.apply(CLang.Formats.Source));
	}
}