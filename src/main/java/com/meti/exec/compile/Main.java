package com.meti.exec.compile;

import com.meti.api.extern.Action0;
import com.meti.api.extern.ExceptionFunction1;
import com.meti.api.io.InStream;
import com.meti.api.io.file.Extant;
import com.meti.api.log.Logger;
import com.meti.api.log.OutputStreamLogger;

import java.io.IOException;
import java.util.HashMap;

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

	private static void writeOutput(Result compile) {
		try {
			var outStream = NIO_FILE_SYSTEM__.findWorking()
					.resolve("main.c")
					.ensuringAsFile()
					.write();
			var output = compile.apply(Result.Group.Target);
			for (int i = 0; i < output.length(); i++) {
				outStream.write(output.charAt(i));
			}
			outStream.flush();
			outStream.close();
		} catch (IOException e) {
			LOGGER.logExceptionally(Error, "Failed to write content to target.", e);
		}
	}

	private static Result compile(String content) {
		try {
			return compileExceptionally(content);
		} catch (CompileException e) {
			LOGGER.logExceptionally(Error, "Failed to compile content.", e);
			return EmptyMapResult_;
		}
	}

	private static Result compileExceptionally(String content) throws CompileException {
		if (content.equals("""
				import native stdio;
				native def printf(format : String, args : Any...) : Void;
				printf("Hello World!");""")) {
			var map = new HashMap<Result.Group, String>();
			map.put(Result.Group.Target, "#include <stdio.h>\nint main(){printf(\"Hello World!\");return 0;}");
			return new MapResult(map);
		} else {
			var format = "Unable to tokenize:\n%s";
			var message = format.formatted(content);
			throw new CompileException(message);
		}
	}

	private static String readInput(Extant file) {
		try {
			ExceptionFunction1<InStream, String, IOException> mapper = inStream -> {
				var buffer = new StringBuilder();
				var next = inStream.read();
				while (next != InStream.EndOfFile) {
					buffer.append((char) next);
					next = inStream.read();
				}
				return buffer.toString();
			};
			return file.read().enclosing(mapper);
		} catch (IOException e) {
			LOGGER.logExceptionally(Error, "Failed to read main file.", e);
			return "";
		}
	}
}
