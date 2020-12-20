package com.meti.api.io;

import java.io.IOException;
import java.util.List;

public interface Directory {
	List<Path> list() throws IOException;

	List<Path> walk() throws IOException;
}
