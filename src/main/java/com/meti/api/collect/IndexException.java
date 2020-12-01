package com.meti.api.collect;

public class IndexException extends Exception {
	private IndexException(String message) {
		super(message);
	}

	public IndexException() {
		super();
	}

	public static IndexException IndexException(String message) {
		return new IndexException(message);
	}
}
