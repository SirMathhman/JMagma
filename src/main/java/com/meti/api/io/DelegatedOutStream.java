package com.meti.api.io;

import java.io.IOException;
import java.util.Objects;

public class DelegatedOutStream implements OutStream {
	private final OutStream parent;

	public DelegatedOutStream(OutStream parent) {
		this.parent = parent;
	}

	@Override
	public void write(char b) throws IOException {
		parent.write(b);
	}

	@Override
	public void flush() throws IOException {
		parent.flush();
	}

	public OutStream parent() {
		return parent;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (DelegatedOutStream) obj;
		return Objects.equals(this.parent, that.parent);
	}

	@Override
	public int hashCode() {
		return Objects.hash(parent);
	}

	@Override
	public String toString() {
		return "DelegatedOutStream[" +
		       "parent=" + parent + ']';
	}

}
