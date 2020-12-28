package com.meti.compile;

import com.meti.api.core.Supplier;
import com.meti.api.io.File;
import com.meti.compile.process.ProcessException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.meti.compile.MagmaTokenizationStage.MagmaTokenizationStage_;

public class MagmaCompiler implements Compiler<TargetType, File> {
	static final MagmaCompiler MagmaCompiler_ = new MagmaCompiler();
	private final RenderStage renderStage = CRenderStage.CRenderStage_;
	private final ProcessorStage processorStage = new ProcessorStageImpl();

	private MagmaCompiler() {
	}

	@Override
	public List<File> compile(Source source, Target<TargetType, File> target) throws IOException, CompileException {
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

	private Result<TargetType> compileContent(Script script, String content) throws TokenizationException, ProcessException {
		var nodes = MagmaTokenizationStage_.tokenizeAll(content);
		var post = processorStage.process(script, nodes);
		return renderStage.render(script, post);
	}
}