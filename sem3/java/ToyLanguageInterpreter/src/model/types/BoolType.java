package model.types;

import model.value.BoolValue;
import model.value.Value;

public class BoolType implements Type {

    @Override
    public boolean equals(Type another_type) {
        ///this is to check if another type is an instance of the BoolType class
        return another_type instanceof BoolType;
    }

    @Override
    public Value get_default()
    {
        return new BoolValue(false);
    }

    @Override
    public Type deep_copy() {
        return new BoolType();
    }

    @Override
    public String toString() {
        return "bool";
    }
}
