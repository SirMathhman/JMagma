import _.Memory;
import _.Memory.Sizes;
import com.meti.api.core.String.

const String = class (array : CharArray) => {
    const length = array.length - 1;
    const apply = array.apply;

    const copyToBuffer = (buffer : CharArray, offset = 0) => {
        for(let i = offset; i < array.length; i++) {
            buffer.set(i, array(i));
        }
    }

    const copy = (const allocator : Allocator = DefaultAllocator) =>
        CharArrays(array.length, allocator)
             .peek(copyToBuffer)
             .map(String);

    const ++= (other : String)(in const allocator : Allocator = DefaultAllocator)  CharArrays(length + other.length + 1)
            .peek(copyToBuffer)
            .peek(other.copyToBuffer(_, other.length))
            .peek(_.set(_.length - 1, '\0'))

}

const Strings = singleton {
    const empty = String();
}