package com.meti.compile;

import com.meti.api.io.Directory;
import com.meti.compile.path.NIOScriptPath;
import com.meti.compile.path.ScriptPath;
import org.junit.jupiter.api.Assertions;

import static com.meti.api.io.NIOFileSystem.FileSystem_;
import static com.meti.compile.MagmaCompiler.MagmaCompiler;

public class FeatureTest {
	protected void assertCompile(String expected, String source) {
		Directory directory = FileSystem_.RootPath().asDirectory().resolveDirectory("source");
		ScriptPath scriptPath = new NIOScriptPath(directory);
		String actual = MagmaCompiler(scriptPath).compile(source);
		Assertions.assertEquals(expected.replace("    ", "\t"), actual);
	}
}
