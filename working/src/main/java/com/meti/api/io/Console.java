package com.meti.api.io;

public class Console extends DelegatedOutStream {
	public static final Console Console_ = new Console(new JavaOutStream(System.out));

	public Console(OutStream parent) {
		super(parent);
	}
}
