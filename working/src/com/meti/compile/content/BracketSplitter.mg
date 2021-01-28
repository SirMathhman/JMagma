import com.meti.{
	api.{
		collect.Stream,
		string.[String, Strings]
	},
	content {
		Splitter,
		State,
		ListState
	}
}

object BracketSplitter {
	def process(state : State, c : I8) =>
		if (c == '}' && state.isShallow()) state.reset().append('}').advance()
		elif (c == ';' && state.isLevel())  state.advance()
		elif (c == '{') state.sink().append(c)
		elif (c == '}') state.surface().append(c)
		else state.append(c)

	def processAll(content : String) =>
		try Streams.ofIntRange(0, content.length())
			.map(_).fold(ListState.empty, process)
		catch ListState.empty;

	const stream = (content : String) =>
		processAll(content)
		.advance()
		.stream()
		.filter(!Strings.isBlank(_))
		.map(String::trim);

	out const Splitter = () => Splitter(this);
}