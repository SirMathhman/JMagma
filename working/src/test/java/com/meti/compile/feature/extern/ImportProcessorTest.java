package com.meti.compile.feature.extern;

import com.meti.compile.ListScript;
import com.meti.compile.process.ProcessException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.meti.compile.feature.extern.ImportProcessor.ImportProcessor_;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class ImportProcessorTest {

	@Test
	void process() throws ProcessException {
		var current = new ListScript(List.of("com", "com/meti", "compile"), "Main");
		var importScript = new ListScript(List.of("com", "com/meti", "api", "external"), "StandardIO");
		var importNode = new Import(importScript);
		var result = ImportProcessor_.process(current, importNode);
		assertEquals("#include \"../api/external/StandardIO.h\"\n", result.render());
	}

	@Test
	void resolve() {
		var from = List.of("com", "com/meti", "compile");
		var to = List.of("com", "com/meti", "api", "external");
		var result = ImportProcessor_.resolve(from, to);
		assertIterableEquals(List.of("..", "api", "external"), result);
	}

	@Test
	void resolveRight() {
		var from = List.of("com", "com/meti", "api", "external");
		var to = List.of("com", "com/meti", "compile");
		var result = ImportProcessor_.resolve(from, to);
		assertIterableEquals(List.of("..", "..", "compile"), result);
	}
}