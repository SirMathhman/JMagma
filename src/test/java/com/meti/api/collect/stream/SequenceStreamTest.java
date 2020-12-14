package com.meti.api.collect.stream;

import com.meti.api.collect.list.ArrayList;
import com.meti.api.collect.string.Strings;
import org.junit.jupiter.api.Test;

import static com.meti.api.collect.stream.SequenceStream.SequenceStream;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SequenceStreamTest {
	@Test
	void getEmpty() throws StreamException {
		assertFalse(SequenceStream(ArrayList.of(Strings::equalsTo))
				.head()
				.isPresent());
	}

	@Test
	void getPresent() throws StreamException {
		assertTrue(SequenceStream(ArrayList.of(Strings::equalsTo, "test0"))
				.head()
				.isPresent());
	}
}