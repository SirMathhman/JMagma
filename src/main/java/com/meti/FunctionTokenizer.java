package com.meti;

import com.meti.FunctionType.FunctionTypeBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.meti.Field.Field;
import static com.meti.Field.*;
import static com.meti.FunctionType.FunctionType;
import static com.meti.FunctionType.WithReturn;
import static com.meti.ImmutableStrategyBuffer.EmptyBuffer;
import static com.meti.Implementation.Both;
import static com.meti.Implementation.Implementation;
import static com.meti.ParameterStrategy.ParameterStrategy_;

public class FunctionTokenizer extends StringTokenizer<Node> {
    public FunctionTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        /*
        Valid:

        def main() : Int => {return 0;}
        def main() => {return 0;}
        def main() : Int
        () : Int
        () : Int => {return 0;}
         */
        if (isFunction()) {
            WithName withFlags = extractHeader();
            if (content.contains(":")) {
                if (content.contains("=>")) {
                    Node function = attachValue(withFlags);
                    return Optional.of(function);
                } else {
                    //TODO: add abstractions
                    return Optional.empty();
                }
            } else {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    private WithName extractHeader() {
        String headerSlice = content.substring(0, content.indexOf('('));
        String headerTrim = headerSlice.trim();
        WithName withName = extractName(Field(), headerTrim);
        return extractFlags(withName, headerTrim);
    }

    private WithName extractName(None builder, String headerTrim) {
        return builder.withName(headerTrim.contains(" ") ? formatName(headerTrim) : "");
    }

    private WithName extractFlags(WithName builder, String headerTrim) {
        int lastSpace = headerTrim.lastIndexOf(' ');
        String flagSlice = headerTrim.substring(0, lastSpace);
        String flagTrim = flagSlice.trim();
        return reduceFlags(builder, flagTrim);
    }

    private Node attachValue(WithName identityBuilder) {
        List<Field> parameters = extractParameters();
        Type functionType = extractFunctionType(parameters);
        return complete(identityBuilder
                .withType(functionType)
                .complete(), parameters);
    }

    private Type extractFunctionType(Collection<Field> parameters) {
        Type returnType = extractReturnType();
        return reduceParameters(FunctionType().withReturnType(returnType), parameters);
    }

    private Type extractReturnType() {
        int typeIndex = content.indexOf(':');
        int valueIndex = content.indexOf("=>");
        String typeSlice = formatTypeSlice(typeIndex, valueIndex);
        String typeTrim = typeSlice.trim();
        return new ContentType(typeTrim);
    }

    private String formatTypeSlice(int typeIndex, int valueIndex) {
        return content.contains("=>") ?
                content.substring(typeIndex + 1, valueIndex) :
                content.substring(typeIndex + 1);
    }

    private List<Field> extractParameters() {
        int paramStart = content.indexOf('(');
        int paramEnd = content.indexOf(')');
        String paramSlice = content.substring(paramStart + 1, paramEnd);
        String paramTrim = paramSlice.trim();
        return getCollect(paramTrim);
    }

    private Node complete(Field identity, Collection<Field> parameters) {
        int separator = content.indexOf("=>");
        String valueSlice = content.substring(separator + 2);
        String valueTrim = valueSlice.trim();
        Node value = new ContentNode(valueTrim);
        return completeWithValue(parameters, identity, value);
    }

    private Node completeWithValue(Collection<Field> parameters, Field identity, Node value) {
        return parameters.stream()
                .reduce(Implementation()
                        .withIdentity(identity)
                        .withValue(value), Both::withParameter, (both, both2) -> both2)
                .complete();
    }

    private Type reduceParameters(WithReturn builder, Collection<Field> parameters) {
        return parameters.stream()
                .map(Field::type)
                .reduce(builder, FunctionTypeBuilder::withParameter, (complete, complete2) -> complete2)
                .complete();
    }

    private List<Field> getCollect(String paramTrim) {
        return IntStream.range(0, paramTrim.length())
                .mapToObj(paramTrim::charAt)
                .reduce(EmptyBuffer, ParameterStrategy_::process, (strategyBuffer, strategyBuffer2) -> strategyBuffer2)
                .complete().trim()
                .map(this::tokenizeParameter)
                .collect(Collectors.toList());
    }

    private WithName reduceFlags(WithName builder, String flagTrim) {
        return Stream.of(flagTrim.split(" "))
                .filter(s -> !s.isBlank())
                .map(String::trim)
                .map(String::toUpperCase)
                .map(Flag::valueOf)
                .reduce(builder, Builder::withFlag, (withName, withName2) -> withName2);
    }

    private String formatName(String headerTrim) {
        int separator = headerTrim.lastIndexOf(' ');
        String nameSlice = headerTrim.substring(separator + 1);
        return nameSlice.trim();
    }

    private Field tokenizeParameter(String s) {
        return new FieldTokenizer(s)
                .tokenize()
                .orElseThrow(() -> invalidateParameter(s));
    }

    private IllegalArgumentException invalidateParameter(String s) {
        String format = "Invalid parameter: %s";
        String message = format.formatted(s);
        return new IllegalArgumentException(message);
    }

    private boolean isFunction() {
        return hasParameters() && hasSeparator();
    }

    private boolean hasSeparator() {
        return content.contains(":") || content.contains("=>");
    }

    private boolean hasParameters() {
        return content.contains("(") && content.contains(")");
    }
}
