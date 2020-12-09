package com.meti.exec.compile.source;

import com.meti.exec.compile.Package;

public interface Source {
	String load(Package p) throws LoadException;
}
