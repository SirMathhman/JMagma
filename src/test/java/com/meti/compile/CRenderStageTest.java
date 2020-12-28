package com.meti.compile;

import com.meti.compile.feature.extern.Directives;
import com.meti.compile.script.ListScript;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.meti.compile.stage.CClass.Header;
import static com.meti.compile.stage.CClass.Source;
import static com.meti.compile.stage.CRenderStage.CRenderStage_;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CRenderStageTest {

	@Test
	void render() {
		var result = CRenderStage_.render(new ListScript(Collections.emptyList(), "Main"), List.of(
				Directives.Include.toNode("<stdio.h>")
		));
		assertEquals("#include \"Main.h\"\n", result.render(Source));
		assertEquals("#include <stdio.h>\n", result.render(Header));
	}
}