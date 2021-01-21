def class String(
	in called const apply : (I16) => I8;
	in const length : () => I16;
) => {
	const equalsTo = (other : String) => {
		const thisLength = length();
		const otherLength = other.length();
		if(thisLength != otherLength) return false;
		for(let i = 0; i < thisLength; i++){
			const thisChar = apply(i);
			const otherChar = apply(i);
			if(thisChar != otherChar) return false;
		}
		return true;
	}
	out const asEquatable = Equatable();
}

def class ArrayString(const array : Ref[I8], const length : I16) => {
	called const apply = (index : I16) =>
		if(index < 0 || index >= length) -1
		else array(_);
	}
	out def asString() => String();
}

out def fromNative(self : Ref[I8]) => {
	let length = -1;
	for(let i = 0;; i += 1){
		if(self(i) == '\0'){
			length = i;
			break;
		}
	}
	return ArrayString(self, length);
}