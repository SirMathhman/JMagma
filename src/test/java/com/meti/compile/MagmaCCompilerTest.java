package com.meti.compile;

import com.meti.api.magma.except.Exception;
import com.meti.compile.io.ListSource;
import com.meti.compile.io.MapLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.meti.compile.MagmaCCompiler.MagmaCCompiler_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MagmaCCompilerTest {

	@Test
	void compile() throws Exception {
		var source = new ListSource(List.of("Main"));
		var loader = new MapLoader(Map.of(source, "def main() : I16 => {return 0;}"));
		MagmaCCompiler_.compile(loader).apply(source).ifPresentOrElse(actual -> {
			assertEquals("""
					#ifndef Main
					#define Main
					#endif""", actual.apply(CLang.Formats.Header));
			assertEquals("""
					#include "Main.h"
					int main(){return 0;}""", actual.apply(CLang.Formats.Source));
		}, Assertions::fail);
	}
}