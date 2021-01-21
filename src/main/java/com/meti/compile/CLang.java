package com.meti.compile;

import com.meti.compile.io.Result;

import java.util.UnknownFormatConversionException;

public class CLang {
	public CLang() {
	}

	public enum Formats implements Result.Format {
		Source("%s.c"),
		Header("%s.h");

		private final String value;

		Formats(String value) {
			this.value = value;
		}

		@Override
		public String format(String name) {
			try {
				return String.format(value, name);
			} catch (UnknownFormatConversionException e) {
				var format = "Failed to format '%s' with '%s'.";
				var message = format.formatted(value, name);
				throw new UnsupportedOperationException(message, e);
			}
		}
	}
}
