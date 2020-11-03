const IndexException = class(message : Any) => {
    out const Exception = Exception(message);
}

const invalidLower = "Index '%d' cannot be negative'.";
const invalidUpper = "Index '%d' cannot be equal to or exceed length of '%d'.";

const Container = (length : I16) => {
    const validateBounds = (index : I16) ? IndexException => {
        if(index < 0) throw IndexException(invalidLower.format(&index));
        if(index >= length) throw IndexException(invalidUpper.format(&index, &length));
    }
}

const CharArray = Container >> class(buffer : Ref[I8]) => {
    const set = (index : I16, value : I8) => validateBounds(index, {
        char old = buffer[index];
        buffer[index] = value;
        return old;
    });
    const get = (index : I16) => validateBounds(index, () => buffer[index]);
    const apply = get;
    out const Container = Container(length);
}

const CharArrays = single {
    const apply = (length : I16, in const allocator : Allocator = DefaultAllocator) =>
        allocator.allocate(I8.size * length)
                 .map(CharArray(length, _));
}