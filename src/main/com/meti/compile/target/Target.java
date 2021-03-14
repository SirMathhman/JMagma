package com.meti.compile.target;

import java.io.IOException;

public interface Target {
	void write(String output) throws IOException;
}
