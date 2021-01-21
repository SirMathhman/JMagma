package com.meti.compile.stack;

import com.meti.compile.io.Source;

import java.util.ArrayList;
import java.util.List;

public interface Stack {
	Stack reset(List<Source> imports);

	default Stack reset2(ArrayList<Source> imports){
		return reset(imports);
	}
}
