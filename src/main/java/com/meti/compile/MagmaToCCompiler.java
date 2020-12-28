package com.meti.compile;

import com.meti.api.core.Supplier;
import com.meti.api.io.File;
import com.meti.compile.token.TokenizationException;
import com.meti.compile.process.ParseException;
import com.meti.compile.script.Result;
import com.meti.compile.script.Script;
import com.meti.compile.script.Source;
import com.meti.compile.script.Target;
import com.meti.compile.stage.CClass;
import com.meti.compile.stage.CGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.meti.compile.stage.CRenderStage.CRenderStage_;
import static com.meti.compile.stage.MagmaToCFormatter.MagmaToCFormatter_;
import static com.meti.compile.stage.MagmaTokenizationStage.MagmaTokenizationStage_;
import static com.meti.compile.stage.MagmaValidator.MagmaValidator_;

public class MagmaToCCompiler implements Compiler<CClass, File> {
	static final Compiler<CClass, File> MagmaCompiler_ = new MagmaToCCompiler();

	private MagmaToCCompiler() {
	}

	private Result<CClass, CGroup> compileContent(Script script, String content) throws TokenizationException, ParseException {
		var tokens = MagmaTokenizationStage_.tokenizeAll(content);
		var validated = MagmaValidator_.process(script, tokens);
		var formatted = MagmaToCFormatter_.process(script, validated);
		return CRenderStage_.render(script, formatted);
	}

	@Override
	public List<File> compile(Source source, Target<CClass, File> target) throws IOException, CompileException {
		var scripts = source.list();
		if (scripts.isEmpty()) {
			throw new CompileException("No scripts were found for source: " + source);
		}
		var intermediates = new ArrayList<File>();
		for (Script script : scripts) {
			Supplier<IOException> doesNotExist = () -> new IOException(script + " does not exist to be read.");
			var input = source.read(script).orElseThrow(doesNotExist);
			var result = compileContent(script, input);
			var intermediate = target.write(script, result);
			intermediates.addAll(intermediate);
		}
		return intermediates;
	}
}