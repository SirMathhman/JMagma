package com.meti.compile;

import com.meti.compile.io.Result;

public class CLang {
	public CLang() {
	}

	public enum Formats implements Result.Format {
		Source("%.c"),
		Header("%.h");

		private final String value;

		Formats(String value) {
			this.value = value;
		}

		@Override
		public String format(String name) {
			return String.format(value, name);
		}
	}
}
