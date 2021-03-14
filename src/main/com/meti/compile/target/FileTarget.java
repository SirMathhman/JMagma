package com.meti.compile.target;

import com.meti.api.io.File;

import java.io.IOException;

public record FileTarget(File targetFile) implements Target {
	public static Target FileTarget(File targetFile) {
		return new FileTarget(targetFile);
	}

	@Override
	public void write(String output) throws IOException {
		targetFile.write(output);
	}
}
