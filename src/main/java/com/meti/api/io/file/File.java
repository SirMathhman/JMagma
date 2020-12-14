package com.meti.api.io.file;

import com.meti.api.core.Equatable;
import com.meti.api.io.InStream;
import com.meti.api.io.OutStream;

import java.io.IOException;

public interface File extends Equatable<File> {
	Path asPath();

	InStream read() throws IOException;

	OutStream write() throws IOException;
}
