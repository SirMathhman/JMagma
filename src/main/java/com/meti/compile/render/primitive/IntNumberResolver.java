package com.meti.compile.render.primitive;

import com.meti.compile.render.node.Node;
import com.meti.compile.render.resolve.Resolver;
import com.meti.compile.render.type.Type;

import java.math.BigInteger;
import java.util.Optional;

import static com.meti.compile.render.primitive.Primitive.*;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;
import static java.util.Optional.empty;

public class IntNumberResolver implements Resolver {
    static final Bounds Bounds8 = Bounds.of(8);
    private static final Bounds Bounds16 = Bounds.of(16);
    private static final Bounds Bounds32 = Bounds.of(32);
    private static final Bounds Bounds64 = Bounds.of(64);
    private final Node current;

    public IntNumberResolver(Node current) {
        this.current = current;
    }

    @Override
    public Optional<Type> resolve() {
        if (current.is(Node.Group.IntNumber)) {
            var value = current.value(BigInteger.class);
            if (Bounds8.contains(value)) return Optional.of(I8);
            if (Bounds16.contains(value)) return Optional.of(I16);
            if (Bounds32.contains(value)) return Optional.of(I32);
            if (Bounds64.contains(value)) return Optional.of(I64);

            var format = "Cannot find bounds for '%s'";
            var message = format.formatted(value.toString());
            throw new IllegalStateException(message);
        }
        return empty();
    }

    public static class Bounds {
        private final BigInteger from;
        private final BigInteger to;

        private Bounds(BigInteger from, BigInteger to) {
            this.from = from;
            this.to = to;
        }

        private static Bounds of(int range) {
            var upperBound = TWO.pow(range - 1).subtract(ONE);
            var lowerBound = upperBound.negate();
            return new Bounds(lowerBound, upperBound);
        }

         boolean contains(BigInteger value) {
            return value.compareTo(from) >= 0 && 0 >= value.compareTo(to);
        }
    }
}
