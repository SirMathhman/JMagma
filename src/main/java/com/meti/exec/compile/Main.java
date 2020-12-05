package com.meti.exec.compile;

import com.meti.api.collect.StreamException;
import com.meti.api.collect.StringBuffer;
import com.meti.api.collect.Strings;
import com.meti.api.extern.Action0;
import com.meti.api.extern.ExceptionFunction1;
import com.meti.api.io.InStream;
import com.meti.api.io.OutStream;
import com.meti.api.io.file.Extant;
import com.meti.api.log.Logger;
import com.meti.api.log.OutputStreamLogger;

import java.io.IOException;

import static com.meti.api.collect.SimpleStringBuffer.StringBuffer;
import static com.meti.api.io.file.nio.NIOFileSystem.NIO_FILE_SYSTEM__;
import static com.meti.api.log.Logger.Level.Error;
import static com.meti.exec.compile.EmptyMapResult.EmptyMapResult_;

public class Main {
	private static final Logger LOGGER = new OutputStreamLogger(System.err);

	public static void main(String[] args) {
		try {
			Action0 action0 = () -> LOGGER.logSimple(Error, "Main file doesn't exist.");
			NIO_FILE_SYSTEM__.findWorking()
					.resolve("Main.mgs")
					.existingAsFile()
					.ifPresentOrElse(Main::compileMain, action0);
		} catch (IOException e) {
			LOGGER.logExceptionally(Error, "Failed to create path for main file.", e);
		}
	}

	private static void compileMain(Extant nioExtant) {
		var content = readInput(nioExtant);
		var compile = compile(content);
		writeOutput(compile);
	}

	private static void writeOutput(Result<Result.Group> compile) {
		try {
			ExceptionFunction1<OutStream, OutStream, StreamException> mapper = stream -> Strings
					.stream(compile.apply(Result.Group.Target))
					.foldLeft(stream, OutStream::write);
			NIO_FILE_SYSTEM__.findWorking()
					.resolve("main.c")
					.ensuringAsFile()
					.write()
					.enclosing(mapper);
		} catch (StreamException | IOException e) {
			LOGGER.logExceptionally(Error, "Failed to write content to target.", e);
		}
	}

	private static Result<Result.Group> compile(String content) {
		try {
			return compileExceptionally(content);
		} catch (CompileException e) {
			LOGGER.logExceptionally(Error, "Failed to compile content.", e);
			return EmptyMapResult_;
		}
	}

	private static Result<Result.Group> compileExceptionally(String content) throws CompileException {
		if (content.equals("""
				import native stdio;
				native def printf(format : String, args : Any...) : Void;
				printf("Hello World!");""")) {
			return new MapResult().with(Result.Group.Target, "#include <stdio.h>\nint main(){printf(\"Hello World!\");return 0;}");
		} else {
			var format = "Unable to tokenize:\n%s";
			var message = format.formatted(content);
			throw new CompileException(message);
		}
	}

	private static String readInput(Extant file) {
		try {
			ExceptionFunction1<InStream, String, StreamException> mapper = inStream -> inStream.stream()
					.map(integer -> (char) (int) integer)
					.foldLeft(StringBuffer(), StringBuffer::append)
					.toString();
			return file.read().enclosing(mapper);
		} catch (IOException | StreamException e) {
			LOGGER.logExceptionally(Error, "Failed to read main file.", e);
			return "";
		}
	}
}
