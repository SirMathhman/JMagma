package com.meti.compile.feature.function;

import com.api.core.EF1;
import com.meti.compile.feature.Type;

import java.util.*;
import java.util.stream.Collectors;

public class FunctionType implements Type {
	private final Type returnType;
	private final List<Type> paramTypes;

	private FunctionType(Type returnType, List<Type> paramTypes) {
		this.returnType = returnType;
		this.paramTypes = paramTypes;
	}

	static P0 FunctionType() {
		return new P0(Collections.emptyList());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FunctionType that = (FunctionType) o;
		return Objects.equals(returnType, that.returnType) &&
		       Objects.equals(paramTypes, that.paramTypes);
	}

	@Override
	public int hashCode() {
		return Objects.hash(returnType, paramTypes);
	}

	@Override
	public Optional<Type> findChild() {
		return Optional.of(returnType);
	}

	@Override
	public String toString() {
		return "FunctionType{" +
		       "returnType=" + returnType +
		       ", paramTypes=" + paramTypes +
		       '}';
	}

	@Override
	public <E extends Exception> Type mapByChildren(EF1<Type, Type, E> mapper) throws E {
		var newParameters = new ArrayList<Type>();
		for (Type paramType : paramTypes) {
			newParameters.add(mapper.apply(paramType));
		}
		return new FunctionType(mapper.apply(returnType), newParameters);
	}

	@Override
	public String render(String value) {
		var paramString = paramTypes.stream()
				.map(Type::render)
				.collect(Collectors.joining(",", "(", ")"));
		return returnType.render("(*" + value + ")" + paramString);
	}

	static record P0(List<Type> paramTypes) {
		P0 withParameter(Type parameter) {
			var list = new ArrayList<>(paramTypes);
			list.add(parameter);
			return new P0(list);
		}

		P1 withReturnType(Type returnType) {
			return new P1(returnType, paramTypes);
		}
	}

	static record P1(Type returnType, List<Type> paramTypes) {
		P1 withParameter(Type parameter) {
			var list = new ArrayList<>(paramTypes);
			list.add(parameter);
			return new P1(returnType, list);
		}

		Type complete() {
			return new FunctionType(returnType, paramTypes);
		}
	}
}
