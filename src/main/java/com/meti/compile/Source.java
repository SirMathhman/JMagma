package com.meti.compile;

import com.meti.api.io.Directory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Source {
	private final Directory root;

	public Source(Directory root) {
		this.root = root;
	}

	public List<Package> list() throws IOException {
		return Collections.emptyList();
	}
}
