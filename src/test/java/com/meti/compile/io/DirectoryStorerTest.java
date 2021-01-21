package com.meti.compile.io;

import com.meti.api.magma.io.Directory;
import com.meti.api.magma.io.IOException_;
import com.meti.compile.CLang;
import com.meti.compile.CompileException;
import com.meti.compile.token.AbstractToken;
import com.meti.compile.token.Content;
import com.meti.compile.token.Token;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.meti.api.java.io.NIOFileSystem.NIOFileSystem_;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DirectoryStorerTest {

	private Directory directory;

	@BeforeEach
	void setUp() throws IOException_ {
		directory = NIOFileSystem_.Root().ensureAsDirectory().resolve("test").ensureAsDirectory();
	}

	@AfterEach
	void tearDown() throws IOException_ {
		directory.delete();
	}

	@Test
	void write() throws IOException_, CompileException {
		var storer = new DirectoryStorer(directory);
		var tokens = Collections.<Token>singletonList(new Content("int main(){return 0;}"));
		var source = Map.<Result.Format, List<Token>>of(CLang.Formats.Source, tokens);
		var result = new MapResult(Map.of(new ListSource(List.of("Main")), new MapMapping(source)));
		storer.write(result);
		assertTrue(directory.resolve("Main.c").exists());
	}
}