package com.meti.api.collect.string;

import com.meti.api.collect.IndexException;
import com.meti.api.collect.list.ArrayList;
import com.meti.api.collect.stream.StreamException;
import com.meti.api.core.FormatException;
import com.meti.api.core.Option;
import com.meti.api.core.Primitives;
import com.meti.api.extern.Function0;
import com.meti.api.extern.Function1;

import static com.meti.api.collect.string.SimpleStringBuffer.StringBuffer;
import static com.meti.api.core.FormatException.FormatException;
import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;

public class Strings {
	public Strings() {
	}

	public static Option<Integer> firstString(String self, String item) {
		var length = item.length();
		for (int i = 0; i < self.length() - length + 1; i++) {
			var slice = slice(self, i, i + length);
			if (equalsTo(slice, item)) {
				return Some(i);
			}
		}
		return None();
	}

	public static String replaceFirst(String format, String source, String destination) {
		Function1<Integer, String> split = index -> {
			var before = slice(format, 0, index);
			var after = slice(format, index + 2, format.length());
			return before + destination + after;
		};
		return firstString(format, source)
				.map(split)
				.orElse(format);
	}

	public static String format(String format, String... strings) {
		var length = strings.length;
		var value = format;
		for (int i = 0; i < length; i++) {
			value = replaceFirst(value, "%o", strings[i]);
		}
		return value;
	}

	public static String toUpperCase(String value) {
		var buffer = StringBuffer.Empty;
		for (int i = 0; i < value.length(); i++) {
			var c = value.charAt(i);
			var asUpperCase = Primitives.toUpperCase(c);
			buffer = buffer.add(asUpperCase);
		}
		return buffer.asString();
	}

	public static String slice(String self, int from, int to) {
		try {
			return ArrayList.range(from, to, Integer::compareTo, i -> i + 1)
					.stream()
					.map(index -> self.charAt(index))
					.foldLeftExceptionally(StringBuffer(), StringBuffer::add)
					.toString();
		} catch (StreamException | IndexException e) {
			return "";
		}
	}

	public static Option<Integer> firstChar(String self, Function1<Character, Boolean> predicate) {
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
		Function1<Character, Boolean> isNotWhitespace = c -> !Primitives.isWhiteSpace(c);
		Function1<Integer, String> withFirst = first -> last(self, isNotWhitespace)
				.filter(last -> last > first)
				.map(last -> slice(self, first, last + 1))
				.orElseGet(() -> slice(self, first, self.length()));
		Function0<String> withoutFirst = () -> last(self, isNotWhitespace)
				.map(last -> slice(self, 0, last + 1))
				.orElse(self);
		return firstChar(self, isNotWhitespace)
				.map(withFirst)
				.orElseGet(withoutFirst);
	}

	public static int length(String self) {
		return self.length();
	}

	public static boolean equalsTo(String self, String other) {
		return compareTo(self, other) == 0;
	}

	public static String concat(String self, String other) {
		return self + other;
	}

	public static int compareTo(String self, String other) {
		var lengthDelta = self.length() - other.length();
		var charDelta = 0;
		var minLength = Math.min(self.length(), other.length());
		for (int i = 0; i < minLength; i++) {
			charDelta += self.charAt(i) - other.charAt(i);
		}
		return lengthDelta + charDelta;
	}

	public static int toInt(String value) throws FormatException {
		if (value.length() == 0) throw FormatException("Value is blank.");
		if (value.charAt(0) == '-') return -toInt(slice(value, 1, value.length()));
		var last = value.charAt(value.length() - 1);
		if (Primitives.isDigit(last)) {
			var slice = slice(value, 0, value.length() - 1);
			var parent = (slice.length() == 0) ? 0 : toInt(slice) * 10;
			return parent + Primitives.asDigit(last);
		}
		throw FormatException("Not an integer: " + value);
	}

	public static String fromInt(int value) {
		if (value == 0) return "0";
		if (value < 0) return "-" + fromInt(-value);
		else {
			var parent = value / 10;
			var parentString = (parent == 0) ? "" : fromInt(parent);
			var digit = value % 10;
			var digitChar = '0' + digit;
			return parentString + (char) digitChar;
		}
	}

	private static boolean isBlank(String self) {
		for (int i = 0; i < self.length(); i++) {
			if (!Primitives.isWhiteSpace(self.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isContent(String self) {
		return !isBlank(self);
	}

	public static String toLowerCase(String value) {
		var buffer = StringBuffer.Empty;
		for (int i = 0; i < value.length(); i++) {
			buffer = buffer.add(Primitives.toLowerCase(value.charAt(i)));
		}
		return buffer.asString();
	}
}
