package com.meti.api.io.file;

import com.meti.api.io.InStream;
import com.meti.api.io.OutStream;

import java.io.IOException;

public interface Extant {
	InStream read() throws IOException;

	OutStream write() throws IOException;
}
