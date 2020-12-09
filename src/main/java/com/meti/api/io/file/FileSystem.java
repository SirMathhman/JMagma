package com.meti.api.io.file;

import java.io.IOException;

public interface FileSystem {
	Directory findWorking() throws IOException;
}
