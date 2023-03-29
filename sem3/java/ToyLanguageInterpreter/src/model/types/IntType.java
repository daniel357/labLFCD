package model.types;

import model.value.IntValue;
import model.value.Value;

public class IntType implements Type{

    @Override
    public boolean equals(Type another_type)
    {
        return another_type instanceof IntType;
    }

    @Override
    public Value get_default() {
        return new IntValue(0);
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public Type deep_copy() {
        return new IntType();
    }
}
