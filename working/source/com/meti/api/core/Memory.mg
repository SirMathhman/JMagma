import com.meti.api.extern.StandardLibrary;

const Sizes = class {
    native def *(size : Size, scalar : I16) => Size;
}();

const Allocator => class {
    in const allocate = [T](size : Size) => Option[T];
}

const DefaultAllocator => class {
    const allocate = [T](size : Size) => {
        const buffer = <T>(malloc(size));
        return if(buffer == 0) None()
               else Some(buffer);
    }
    out const Allocator = Allocator();
}();