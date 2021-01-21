package com.meti.api.magma.io;

public interface File {
	Path delete() throws IOException_;

	String readAsString() throws IOException_;

	File writeAsString(String content) throws IOException_;

	@Deprecated
	Path writeAsString1(String content) throws IOException_;
}
