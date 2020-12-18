package com.meti.compile.feature.function;

import com.meti.compile.feature.Node;
import com.meti.compile.feature.Type;
import com.meti.compile.feature.field.Field;
import com.meti.compile.feature.field.Field.Flag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.meti.compile.feature.field.FieldBuilders.FieldBuilder;
import static com.meti.compile.feature.function.Abstraction.Abstraction;
import static com.meti.compile.feature.function.FunctionType.FunctionType;
import static com.meti.compile.feature.function.Implementation.Implementation;

public interface FunctionBuilder {
	static None withFlag(Flag flag) {
		return new None(Set.of(flag), Collections.emptyList());
	}

	static None withFlags(Set<Flag> flags) {
		return new None(flags, Collections.emptyList());
	}

	static WithName withName(String name) {
		return new WithName(Collections.emptySet(), Collections.emptyList(), name);
	}

	static Type createFunctionType(Type returnType, List<Field> parameters) {
		var prototype = FunctionType()
				.withReturnType(returnType);
		return parameters.stream()
				.map(Field::type)
				.reduce(prototype, FunctionType.P1::withParameter, (p11, p12) -> p12)
				.complete();
	}

	static Field createIdentity(Type identityType, Set<Flag> flags, String name) {
		return FieldBuilder()
				.withFlags(flags)
				.withName(name)
				.withType(identityType)
				.complete();
	}

	Node complete();

	record None(Set<Flag> flags, List<Field> parameters) {
		public WithName withName(String name) {
			return new WithName(flags, parameters, name);
		}
	}

	record WithName(Set<Flag> flags, List<Field> parameters, String name) {
		public WithName withParameters(List<Field> parameters) {
			var newParameters = new ArrayList<>(this.parameters);
			newParameters.addAll(parameters);
			return new WithName(flags, newParameters, name);
		}

		public All withReturn(Type returnType) {
			return new All(flags, name, parameters, returnType);
		}
	}

	record All(Set<Flag> flags, String name, List<Field> parameters, Type returnType) implements FunctionBuilder {
		public WithValue withValue(Node node) {
			return new WithValue(flags, name, parameters, returnType, node);
		}

		@Override
		public Node complete() {
			var identityType = createFunctionType(returnType, parameters);
			var identity = createIdentity(identityType, flags, name);
			return Abstraction(identity, parameters);
		}
	}

	record WithValue(Set<Flag> flags, String name, List<Field> parameters,
	                 Type returnType, Node value) implements FunctionBuilder {
		@Override
		public Node complete() {
			var identityType = createFunctionType(returnType, parameters);
			var identity = createIdentity(identityType, flags, name);
			return Implementation(identity, parameters, value);
		}
	}
}
