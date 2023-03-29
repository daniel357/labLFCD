package model.value;

import model.types.IntType;
import model.types.Type;

public class IntValue implements Value{
    private int value;

    public IntValue(int value)
    {
        this.value = value;
    }

    public int get_value()
    {
        return value;
    }

    @Override
    public Type get_type()
    {
        return new IntType();
    }

    @Override
    public String toString() {
        return "IntValue{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof IntValue))
        {
            return false;
        }
        IntValue cast_object = (IntValue) obj;
        return value == cast_object.value;
    }

    @Override
    public Value deep_copy() {
        return new IntValue(value);
    }
}

