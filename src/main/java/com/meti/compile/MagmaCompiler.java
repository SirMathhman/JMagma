package com.meti.compile;

import com.meti.api.core.Supplier;
import com.meti.api.io.File;
import com.meti.compile.process.ProcessException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.meti.compile.CRenderStage.CRenderStage_;
import static com.meti.compile.MagmaTokenizationStage.MagmaTokenizationStage_;

public class MagmaCompiler implements Compiler<CRenderStage.CClass, File> {
	static final Compiler<CRenderStage.CClass, File> MagmaCompiler_ = new MagmaCompiler();
	private final ProcessorStage processorStage = new ProcessorStageImpl();

	private MagmaCompiler() {
	}

	private Result<CRenderStage.CClass, CRenderStage.CGroup> compileContent(Script script, String content) throws TokenizationException, ProcessException {
		var tokens = MagmaTokenizationStage_.tokenizeAll(content);
		var renderables = processorStage.process(script, tokens);
		return CRenderStage_.render(script, renderables);
	}

	@Override
	public List<File> compile(Source source, Target<CRenderStage.CClass, File> target) throws IOException, CompileException {
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