package model.types;

import model.value.StringValue;
import model.value.Value;

public class StringType implements Type{
    @Override
    public boolean equals(Type anotherType) {
        return anotherType instanceof StringType;
    }

    @Override
    public Value get_default() {
        return new StringValue("");
    }

    @Override
    public Type deep_copy() {
        return new StringType();
    }

    @Override
    public String toString() {
        return "string";
    }
}
