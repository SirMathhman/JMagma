package com.meti;

import java.io.IOException;

public record FileTarget(File targetFile) implements Target {
	static Target FileTarget(File targetFile) {
		return new FileTarget(targetFile);
	}

	@Override
	public void write(String output) throws IOException {
		targetFile.write(output);
	}
}
