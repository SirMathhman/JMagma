package com.meti.api.collect.stream;

import com.meti.api.collect.list.ComparableArrayList;
import com.meti.api.collect.string.Strings;
import org.junit.jupiter.api.Test;

import static com.meti.api.collect.list.ComparableArrayList.of;
import static com.meti.api.collect.stream.SequenceStream.SequenceStream;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SequenceStreamTest {
	@Test
	void getEmpty() throws StreamException {
		assertFalse(SequenceStream(ComparableArrayList.of(Strings::compareTo))
				.head()
				.isPresent());
	}

	@Test
	void getPresent() throws StreamException {
		assertTrue(SequenceStream(of(Strings::compareTo, "test0"))
				.head()
				.isPresent());
	}
}