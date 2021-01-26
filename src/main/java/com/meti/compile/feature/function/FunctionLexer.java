package com.meti.compile.feature.function;

import com.meti.compile.MagmaCompiler;

import java.util.stream.Collectors;

public class FunctionLexer {
	public static String compileFunction(String line, MagmaCompiler compiler) {
		var paramStart = line.indexOf('(');
		var paramEnd = line.indexOf(')');
		var returnsSeparator = line.indexOf(":");
		var bodySeparator = line.indexOf("=>");
		var keysSlice = line.substring(0, paramStart);
		var keysString = keysSlice.trim();
		var space = keysString.lastIndexOf(' ');
		var nameSlice = keysString.substring(space + 1);
		var name = nameSlice.trim();
		var paramSlice = line.substring(paramStart + 1, paramEnd);
		var paramString = paramSlice.trim();
		var parameters = MagmaCompiler.splitSequence(paramString)
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(compiler::compileField)
				.collect(Collectors.toList());
		var typeSlice = line.substring(returnsSeparator + 1, bodySeparator);
		var typeString = typeSlice.trim();
		var type = compiler.compileType(typeString);

		var bodySlice = line.substring(bodySeparator + 2);
		var bodyString = bodySlice.trim();
		var body = compiler.compileNode(bodyString);

		var renderedParameters = parameters.stream().collect(Collectors.joining(",", "(", ")"));
		return "%s %s%s%s".formatted(type, name, renderedParameters, body);
	}

	public static boolean isFunction(String line) {
		return line.startsWith("def");
	}
}