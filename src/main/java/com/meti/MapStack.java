package com.meti;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MapStack implements Stack {
	private final LinkedList<Context> contexts = new LinkedList<>();

	@Override
	public Optional<Field> apply(String name) {
		return contexts.stream()
				.map(context -> context.apply(name))
				.flatMap(Optional::stream)
				.findFirst();
	}

	@Override
	public Optional<Script> current() {
		return Optional.ofNullable(contexts.peek())
				.map(Context::createScript);
	}

	@Override
	public Stack define(Script script, Field field) {
		return applyToHead(script, context -> context.define(field));
	}

	private MapStack applyToHead(Script script, F1<Context, Context> mapper) {
		var context = contexts.poll();
		if (context == null) {
			context = new Context(script);
		}
		var newContext = mapper.apply(context);
		contexts.push(newContext);
		return this;
	}

	@Override
	public Stack enter(Script script) {
		return applyToHead(script, Context::enter);
	}

	@Override
	public Stack exit(Script script) {
		return applyToHead(script, Context::exit);
	}

	@Override
	public boolean isDefined(String name) {
		return contexts.stream()
				.map(context -> context.apply(name))
				.flatMap(Optional::stream)
				.findAny()
				.isPresent();
	}

	@Override
	public Stack center(Script script) {
		var scriptList = script.streamAll().collect(Collectors.toCollection(ArrayList::new));
		scriptList.add(script);
		var contextsToRemove = contexts.stream()
				.filter(context -> !scriptList.contains(context.createScript()))
				.collect(Collectors.toList());
		contexts.removeAll(contextsToRemove);
		contexts.push(new Context(script));
		return this;
	}

	private class Frame {
		private final List<Field> scope = new ArrayList<>();

		public Optional<Field> apply(String name) {
			return scope.stream()
					.filter(field -> field.isNamed(name))
					.findFirst();
		}

		public Frame define(Field field) {
			scope.add(field);
			return this;
		}
	}

	private class Context {
		private final Script script;
		private final LinkedList<Frame> frames = new LinkedList<>();

		private Context(Script script) {
			this.script = script;
		}

		public Optional<Field> apply(String name) {
			return frames.stream()
					.map(frame -> frame.apply(name))
					.flatMap(Optional::stream)
					.findFirst();
		}

		public Script createScript() {
			return script;
		}

		public Context define(Field field) {
			Frame frame = frames.peek();
			if (frame == null) {
				frame = new Frame();
				frames.add(frame);
			}
			frame.define(field);
			return this;
		}

		public Context enter() {
			frames.push(new Frame());
			return this;
		}

		public Context exit() {
			frames.pop();
			return this;
		}
	}
}
