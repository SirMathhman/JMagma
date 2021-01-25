class def Option[~, T](
	in const super : ~,
	in const orElse : (~ = super, T) => T;
);

class def Some[T](value : T) => {
	const orElse = (other : T) => value;
	out const Option = () => Option(this);
}

class def None[T]() => {
	const orElse = (other : T) => other;
	out const Option = () => Option(this);
}

def supply(option : Option[?,  I16]) => option.orElse(-1);
def main() => supply(Some(0));