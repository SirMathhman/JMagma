package com.meti.compile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Compiler {
	static String compile(String content) {
		var lines = split(content);
		var output = new ArrayList<String>();
		for (String line : lines) {
			output.add(compileNode(line));
		}
		return String.join("", output);
	}

	static String compileNode(String line) {
		String outputLine;
		if (line.contains(":") && line.contains("=")) {
			outputLine = compileField(line) + ";";
		} else if (line.startsWith("{") && line.endsWith("}")) {
			var bodySlice = line.substring(1, line.length() - 1);
			var body = bodySlice.trim();
			var compiledBody = compile(body);
			outputLine = "{%s}".formatted(compiledBody);
		} else if (isCondition(line, "if")) {
			return compileCondition(line, "if");
		} else if (isCondition(line, "while")) {
			return compileCondition(line, "while");
		} else if (line.startsWith("else")) {
			var bodySlice = line.substring(4);
			var bodyString = bodySlice.trim();
			return "else%s".formatted(compile(bodyString));
		} else if (isCondition(line, "elif")) {
			return compileCondition(line, "else if");
		} else if (line.equals("true")) {
			return "1";
		} else if (line.equals("false")) {
			return "0";
		} else if (line.startsWith("def")) {
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
			var parameters = splitSequence(paramString)
					.filter(s -> !s.isBlank())
					.map(String::trim)
					.map(Compiler::compileField)
					.collect(Collectors.toList());
			var typeSlice = line.substring(returnsSeparator + 1, bodySeparator);
			var typeString = typeSlice.trim();
			var bodySlice = line.substring(bodySeparator + 2);
			var bodyString = bodySlice.trim();
			var body = compileNode(bodyString);

			var renderedParameters = parameters.stream().collect(Collectors.joining(",", "(", ")"));
			return "%s %s%s%s".formatted(typeString, name, renderedParameters, body);
		} else if (line.startsWith("return ")) {
			var valueSlice = line.substring(7);
			var valueString = valueSlice.trim();
			var value = compileNode(valueString);
			return "return %s;".formatted(value);
		} else if (line.contains("(") && line.endsWith(")")) {
			var separator = line.indexOf('(');
			var callerSlice = line.substring(0, separator);
			var callerString = callerSlice.trim();
			var caller = compileNode(callerString);
			var argumentsSlice = line.substring(separator + 1, line.length() - 1);
			var argumentsString = argumentsSlice.trim();
			var arguments = splitSequence(argumentsString)
					.filter(s -> !s.isBlank())
					.map(String::trim)
					.map(Compiler::compileNode)
					.collect(Collectors.toList());
			return caller + arguments.stream().collect(Collectors.joining(",", "(", ")"));
		} else if (line.startsWith("struct ") && line.contains("{") && line.endsWith("}")) {
			var separator = line.indexOf('{');
			var nameSlice = line.substring(7, separator);
			var name = nameSlice.trim();
			var membersSlice = line.substring(separator + 1, line.length() - 1);
			var membersString = membersSlice.trim();
			var members = splitSequence(membersString)
					.filter(s -> !s.isBlank())
					.map(String::trim)
					.map(Compiler::compileField)
					.collect(Collectors.toList());
			var renderedMembers = members.stream()
					.map(value -> value + ";")
					.collect(Collectors.joining("", "{", "}"));
			return "struct " + name + renderedMembers + ";";
		} else if (line.startsWith("[") && line.contains("]") &&
		           line.contains("{") && line.contains("}")) {
			var castEnd = line.indexOf(']');
			var castSlice = line.substring(1, castEnd);
			var cast = castSlice.trim();
			var bodyStart = line.indexOf('{');
			var bodySlice = line.substring(bodyStart + 1, line.length() - 1);
			var bodyString = bodySlice.trim();
			var arguments = splitSequence(bodyString)
					.filter(s -> !s.isBlank())
					.map(String::trim)
					.map(Compiler::compileNode)
					.collect(Collectors.toList());
			return arguments.stream().collect(Collectors.joining(",", "(", ")"));
		} else if (line.contains("=>")) {
			var separator = line.indexOf("=>");
			var firstSlice = line.substring(0, separator);
			var first = firstSlice.trim();
			var structure = compileNode(first);
			var memberSlice = line.substring(separator + 2);
			var memberString = memberSlice.trim();
			return "%s.%s".formatted(structure, memberString);
		} else if (line.startsWith("&")) {
			var slice = line.substring(1);
			var string = slice.trim();
			var node = compileNode(string);
			return "&%s".formatted(node);
		} else if (line.startsWith("*")) {
			var slice = line.substring(1);
			var string = slice.trim();
			var node = compileNode(string);
			return "*%s".formatted(node);
		} else if (line.startsWith("(") && line.endsWith(")")) {
			var slice = line.substring(1, line.length() - 1);
			var string = slice.trim();
			var node = compileNode(string);
			return "(%s)".formatted(node);
		} else if (line.contains("=")) {
			var separator = line.indexOf('=');
			var beforeSlice = line.substring(0, separator);
			var beforeString = beforeSlice.trim();
			var before = compileNode(beforeString);
			var afterSlice = line.substring(separator + 1);
			var afterString = afterSlice.trim();
			var after = compileNode(afterString);
			return "%s=%s;".formatted(before, after);
		} else {
			outputLine = line;
		}
		return outputLine;
	}

	static Stream<String> splitSequence(String sequence) {
		return Arrays.stream(sequence.split(","));
	}

	static String compileField(String line) {
		var typeSeparator = line.indexOf(':');
		var valueSeparator = line.indexOf('=');
		var keysSlice = line.substring(0, typeSeparator);
		var keysString = keysSlice.trim();
		var space = keysString.lastIndexOf(' ');
		var nameSlice = keysString.substring(space + 1);
		var name = nameSlice.trim();
		var typeSlice = line.substring(typeSeparator + 1, valueSeparator);
		var typeString = typeSlice.trim();
		var valueSlice = line.substring(valueSeparator + 1);
		var valueString = valueSlice.trim();
		var value = compile(valueString);
		return "%s %s=%s".formatted(typeString, name, value);
	}

	static String compileCondition(String line, final String type) {
		var condStart = line.indexOf('(');
		var condEnd = line.indexOf(')');
		var conditionSlice = line.substring(condStart + 1, condEnd);
		var conditionString = conditionSlice.trim();
		var condition = compileNode(conditionString);
		var bodySlice = line.substring(condEnd + 1);
		var bodyString = bodySlice.trim();
		var body = compileNode(bodyString);
		return "%s(%s)%s".formatted(type, condition, body);
	}

	static boolean isCondition(String line, String condition) {
		return line.startsWith(condition);
	}

	static ArrayList<String> split(String content) {
		ArrayList<String> lines = new ArrayList<String>();
		var buffer = new StringBuilder();
		var depth = 0;
		for (int i = 0; i < content.length(); i++) {
			var c = content.charAt(i);
			if (c == '}' && depth == 1) {
				depth = 0;
				buffer.append('}');
				lines.add(buffer.toString());
				buffer = new StringBuilder();
			} else if (c == ';' && depth == 0) {
				lines.add(buffer.toString());
				buffer = new StringBuilder();
			} else {
				if (c == '{') depth++;
				if (c == '}') depth--;
				buffer.append(c);
			}
		}
		lines.add(buffer.toString());
		lines.removeIf(String::isBlank);
		return lines;
	}
}