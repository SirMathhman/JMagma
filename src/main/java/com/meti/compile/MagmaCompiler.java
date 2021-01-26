package com.meti.compile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MagmaCompiler {

	public static final MagmaCompiler MagmaCompiler_ = new MagmaCompiler();

	private MagmaCompiler() {
	}

	private static String compileAssignment(String line) {
		var separator = line.indexOf('=');
		var beforeSlice = line.substring(0, separator);
		var beforeString = beforeSlice.trim();
		var before = MagmaCompiler_.compileNode(beforeString);
		var afterSlice = line.substring(separator + 1);
		var afterString = afterSlice.trim();
		var after = MagmaCompiler_.compileNode(afterString);
		return "%s=%s;".formatted(before, after);
	}

	private static boolean isAssignment(String line) {
		return line.contains("=");
	}

	private static String compileQuantity(String line) {
		var slice = line.substring(1, line.length() - 1);
		var string = slice.trim();
		var node = MagmaCompiler_.compileNode(string);
		return "(%s)".formatted(node);
	}

	private static boolean isQuantity(String line) {
		return line.startsWith("(") && line.endsWith(")");
	}

	private static String compileDereference(String line) {
		var slice = line.substring(1);
		var string = slice.trim();
		var node = MagmaCompiler_.compileNode(string);
		return "*%s".formatted(node);
	}

	private static boolean isDereference(String line) {
		return line.startsWith("*");
	}

	private static String compileReference(String line) {
		var slice = line.substring(1);
		var string = slice.trim();
		var node = MagmaCompiler_.compileNode(string);
		return "&%s".formatted(node);
	}

	private static boolean isReference(String line) {
		return line.startsWith("&");
	}

	private static String compileAccesor(String line) {
		var separator = line.indexOf("=>");
		var firstSlice = line.substring(0, separator);
		var first = firstSlice.trim();
		var structure = MagmaCompiler_.compileNode(first);
		var memberSlice = line.substring(separator + 2);
		var memberString = memberSlice.trim();
		return "%s.%s".formatted(structure, memberString);
	}

	private static boolean isAccessor(String line) {
		return line.contains("=>");
	}

	private static String compileConstruction(String line) {
		var bodyStart = line.indexOf('{');
		var bodySlice = line.substring(bodyStart + 1, line.length() - 1);
		var bodyString = bodySlice.trim();
		var arguments = splitSequence(bodyString)
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(MagmaCompiler_::compileNode)
				.collect(Collectors.toList());
		return arguments.stream().collect(Collectors.joining(",", "{", "}"));
	}

	private static boolean isConstruction(String line) {
		return line.startsWith("[") && line.contains("]") &&
		       line.contains("{") && line.contains("}");
	}

	private static String compileStructure(String line) {
		var separator = line.indexOf('{');
		var nameSlice = line.substring(7, separator);
		var name = nameSlice.trim();
		var membersSlice = line.substring(separator + 1, line.length() - 1);
		var membersString = membersSlice.trim();
		var members = splitSequence(membersString)
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(MagmaCompiler::compileField)
				.collect(Collectors.toList());
		var renderedMembers = members.stream()
				.map(value -> value + ";")
				.collect(Collectors.joining("", "{", "}"));
		return "struct " + name + renderedMembers + ";";
	}

	private static boolean isStructure(String line) {
		return line.startsWith("struct ") && line.contains("{") && line.endsWith("}");
	}

	private static String compileInvocation(String line) {
		var separator = line.indexOf('(');
		var callerSlice = line.substring(0, separator);
		var callerString = callerSlice.trim();
		var caller = MagmaCompiler_.compileNode(callerString);
		var argumentsSlice = line.substring(separator + 1, line.length() - 1);
		var argumentsString = argumentsSlice.trim();
		var arguments = splitSequence(argumentsString)
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(MagmaCompiler_::compileNode)
				.collect(Collectors.toList());
		return caller + arguments.stream().collect(Collectors.joining(",", "(", ")"));
	}

	private static boolean isInvocation(String line) {
		return line.contains("(") && line.endsWith(")");
	}

	private static String compileReturn(String line) {
		var valueSlice = line.substring(7);
		var valueString = valueSlice.trim();
		var value = MagmaCompiler_.compileNode(valueString);
		return "return %s;".formatted(value);
	}

	private static boolean isReturn(String line) {
		return line.startsWith("return ");
	}

	private static String compileFunction(String line) {
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
				.map(MagmaCompiler::compileField)
				.collect(Collectors.toList());
		var typeSlice = line.substring(returnsSeparator + 1, bodySeparator);
		var typeString = typeSlice.trim();
		var type = compileType(typeString);

		var bodySlice = line.substring(bodySeparator + 2);
		var bodyString = bodySlice.trim();
		var body = MagmaCompiler_.compileNode(bodyString);

		var renderedParameters = parameters.stream().collect(Collectors.joining(",", "(", ")"));
		return "%s %s%s%s".formatted(type, name, renderedParameters, body);
	}

	private static boolean isFunction(String line) {
		return line.startsWith("def");
	}

	private static String compileElif(String line) {
		return compileCondition(line, "else if");
	}

	private static boolean isElif(String line) {
		return isCondition(line, "elif");
	}

	private static String compileElse(String line) {
		var bodySlice = line.substring(4);
		var bodyString = bodySlice.trim();
		return "else%s".formatted(MagmaCompiler_.compileLines(bodyString));
	}

	private static boolean isElse(String line) {
		return line.startsWith("else");
	}

	private static String compileWhile(String line) {
		return compileCondition(line, "while");
	}

	private static boolean isWhile(String line) {
		return isCondition(line, "while");
	}

	private static String compileIf(String line) {
		return compileCondition(line, "if");
	}

	private static boolean isIf(String line) {
		return isCondition(line, "if");
	}

	private static String compileBlock(String line) {
		String outputLine;
		var bodySlice = line.substring(1, line.length() - 1);
		var bodyString = bodySlice.trim();
		var body = MagmaCompiler_.compileLines(bodyString);
		outputLine = "{%s}".formatted(body);
		return outputLine;
	}

	private static boolean isBlock(String line) {
		return line.startsWith("{") && line.endsWith("}");
	}

	private static String compileDeclaration(String line) {
		return "%s;".formatted(compileField(line));
	}

	private static boolean isDeclaration(String line) {
		return line.contains(":") && line.contains("=");
	}

	private static Stream<String> splitSequence(String sequence) {
		return Arrays.stream(sequence.split(","));
	}

	private static String compileField(String line) {
		var typeSeparator = line.indexOf(':');
		var valueSeparator = line.indexOf('=');
		var keysSlice = line.substring(0, typeSeparator);
		var keysString = keysSlice.trim();
		var space = keysString.lastIndexOf(' ');
		var nameSlice = keysString.substring(space + 1);
		var name = nameSlice.trim();
		var extent = valueSeparator == -1 ? line.length() : valueSeparator;
		var typeSlice = line.substring(typeSeparator + 1, extent);
		var typeString = typeSlice.trim();
		if (valueSeparator == -1) {
			return "%s %s".formatted(compileType(typeString), name);
		} else {
			var valueSlice = line.substring(valueSeparator + 1);
			var valueString = valueSlice.trim();
			var value = MagmaCompiler_.compileNode(valueString);
			return "%s %s=%s".formatted(compileType(typeString), name, value);
		}
	}

	private static String compileType(String content) {
		if (content.equals("I16")) return "int";
		return content;
	}

	private static String compileCondition(String line, final String type) {
		var condStart = line.indexOf('(');
		var condEnd = line.indexOf(')');
		var conditionSlice = line.substring(condStart + 1, condEnd);
		var conditionString = conditionSlice.trim();
		var condition = MagmaCompiler_.compileNode(conditionString);
		var bodySlice = line.substring(condEnd + 1);
		var bodyString = bodySlice.trim();
		var body = MagmaCompiler_.compileNode(bodyString);
		return "%s(%s)%s".formatted(type, condition, body);
	}

	private static boolean isCondition(String line, String condition) {
		return line.startsWith(condition);
	}

	private static ArrayList<String> split(String content) {
		ArrayList<String> lines = new ArrayList<>();
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

	String compileLines(String content) {
		return split(content).stream()
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(this::compileNode)
				.collect(Collectors.joining());
	}

	private String compileNode(String line) {
		if (isBlock(line)) {
			return compileBlock(line);
		} else if (isFunction(line)) {
			return compileFunction(line);
		} else if (isDeclaration(line)) {
			return compileDeclaration(line);
		} else if (isIf(line)) {
			return compileIf(line);
		} else if (isWhile(line)) {
			return compileWhile(line);
		} else if (isElse(line)) {
			return compileElse(line);
		} else if (isElif(line)) {
			return compileElif(line);
		} else if (line.equals("true")) {
			return "1";
		} else if (line.equals("false")) {
			return "0";
		} else if (isReturn(line)) {
			return compileReturn(line);
		} else if (isInvocation(line)) {
			return compileInvocation(line);
		} else if (isStructure(line)) {
			return compileStructure(line);
		} else if (isConstruction(line)) {
			return compileConstruction(line);
		} else if (isAccessor(line)) {
			return compileAccesor(line);
		} else if (isReference(line)) {
			return compileReference(line);
		} else if (isDereference(line)) {
			return compileDereference(line);
		} else if (isQuantity(line)) {
			return compileQuantity(line);
		} else if (isAssignment(line)) {
			return compileAssignment(line);
		} else {
			return line;
		}
	}
}