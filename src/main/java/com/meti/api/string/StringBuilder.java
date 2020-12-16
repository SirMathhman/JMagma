package com.meti.api.string;

public interface StringBuilder extends Stringable {
	StringBuilder addChar(char c);

	StringBuilder addString(String s);

	StringBuilder addInt(int value);
}
