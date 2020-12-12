package com.meti.api.collect.string;

import com.meti.api.collect.list.ArrayList;
import com.meti.api.collect.list.List;
import com.meti.api.collect.stream.DelegatedStream;
import com.meti.api.collect.stream.EndOfStreamException;
import com.meti.api.collect.stream.Stream;
import com.meti.api.collect.stream.StreamException;
import com.meti.api.core.FormatException;
import com.meti.api.core.Option;
import com.meti.api.core.Primitives;
import com.meti.api.core.Stringable;
import com.meti.api.extern.Function1;

import java.util.Arrays;

import static com.meti.api.collect.stream.SequenceStream.SequenceStream;
import static com.meti.api.collect.string.SimpleStringBuffer.StringBuffer;
import static com.meti.api.core.FormatException.FormatException;
import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;

public class Strings {
	public Strings() {
	}

	public static int indexOfSlice(String self, String slice) {
		if (slice.length() > self.length()) return -1;
		if (equals(self, slice)) return 0;
		for (int i = 0; i < self.length() - slice.length(); i++) {
			var internalSlice = slice(self, i, slice.length() + i);
			if (equals(internalSlice, slice)) {
				return i;
			}
		}
		return -1;
	}

	public static boolean containsSlice(String self, String slice) {
		return indexOfSlice(self, slice) != 0;
	}

	public static String replace(String self, String source, String target) {
		var index = indexOfSlice(self, source);
		if (index == -1) return self;
		else {
			var before = slice(self, 0, index);
			var after = slice(self, index + 1, self.length());
			return concat(concat(before, target), after);
		}
	}

	public static String format(String self, Stringable... stringables) {
		if (!containsSlice(self, "%o") || stringables.length == 0) {
			return self;
		} else {
			var first = stringables[0];
			var post = replace(self, "%o", first.asString());
			var copy = Arrays.copyOfRange(stringables, 1, stringables.length);
			return format(post, copy);
		}
	}

	public static String slice(String self, int from, int to) {
		try {
			var list = ArrayList.range(from, to, Primitives::comparingInts, i -> i + 1);
			return SequenceStream(list)
					.map(self::charAt)
					.foldLeft(StringBuffer(), StringBuffer::add)
					.toString();
		} catch (StreamException e) {
			return "";
		}
	}

	public static Option<Integer> first(String self, Function1<Character, Boolean> predicate) {
		for (int i = 0; i < self.length(); i++) {
			if (predicate.apply(self.charAt(i))) {
				return Some(i);
			}
		}
		return None();
	}

	public static Option<Integer> last(String self, Function1<Character, Boolean> predicate) {
		for (int i = self.length() - 1; i >= 0; i--) {
			if (predicate.apply(self.charAt(i))) {
				return Some(i);
			}
		}
		return None();
	}

	public static String trim(String self) {
		var first = first(self, Primitives::isWhitespace).orElse(0);
		var last = last(self, Character::isWhitespace).orElse(self.length());
		return slice(self, first, last);
	}

	public static long hash(CharSequence self) {
		long value = 0;
		for (int i = 0; i < self.length(); i++) {
			value += Primitives.hash(self.charAt(i));
		}
		return value;
	}

	public static int length(CharSequence self) {
		return self.length();
	}

	public static boolean equals(CharSequence self, CharSequence other) {
		return compareTo(self, other) == 0;
	}

	public static String concat(String self, String other) {
		return self + other;
	}

	public static int compareTo(CharSequence self, CharSequence other) {
		var lengthDelta = self.length() - other.length();
		var charDelta = 0;
		var minLength = Math.min(self.length(), other.length());
		for (int i = 0; i < minLength; i++) {
			charDelta += self.charAt(i) - other.charAt(i);
		}
		return lengthDelta + charDelta;
	}

	public static Stream<Character> stream(String value) {
		return new StringStream(value);
	}

	public static int asInt(String value) throws FormatException {
		if (value.length() == 0) throw FormatException("Value is blank.");
		if (value.charAt(0) == '-') return -asInt(slice(value, 1, value.length()));
		var last = value.charAt(value.length() - 1);
		if (Primitives.isDigit(last)) {
			var slice = slice(value, 0, value.length() - 1);
			var parent = (slice.length() == 0) ? 0 : asInt(slice) * 10;
			return parent + Primitives.asDigit(last);
		}
		throw FormatException("Not an integer: " + value);
	}

	public static String valueOfInt(int value) {
		if (value == 0) return "0";
		if (value < 0) return "-" + valueOfInt(-value);
		else {
			var parent = value / 10;
			var parentString = (parent == 0) ? "" : valueOfInt(parent);
			var digit = value % 10;
			var digitChar = '0' + digit;
			return parentString + (char) digitChar;
		}
	}

	private static class StringStream extends DelegatedStream<Character> {
		private final int length;
		private final String value;
		private int counter;

		StringStream(String value) {
			this.value = value;
			this.length = value.length();
			this.counter = 0;
		}

		@Override
		protected Character get() throws StreamException {
			if (counter < length) {
				return value.charAt(counter++);
			} else {
				throw EndOfStreamException.EndOfStreamException("No more characters exist in the string.");
			}
		}
	}
}
