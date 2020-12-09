package com.meti.exec.compile;

public class Feature {
	public static String formatTarget(String format) {
		String formatted = format;
		while(formatted.contains("  ")){
			formatted = formatted.replace("  ", " ");
		}
		return formatted.trim()
				.replace("\t", "")
				.replace("\n", "");
	}
}
