package com.meti.api.collect.stream;

public class Streams {
	public Streams() {
	}

	public static <T> Stream<T> empty() {
		return new Stream<T>() {
		};
	}
}
