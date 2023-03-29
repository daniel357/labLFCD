package model.types;


import model.value.RefValue;
import model.value.Value;

public class RefType implements Type{
    private final Type inner;

    public RefType(Type inner) {
        this.inner = inner;
    }

    Type getInner() {
        return this.inner;
    }

    @Override
    public boolean equals(Type anotherType) {
        if (anotherType instanceof RefType)
            return inner.equals(((RefType) anotherType).getInner());
        else
            return false;
    }

    @Override
    public Value get_default() {
        return new RefValue(0, inner);
    }

    @Override
    public Type deep_copy() {
        return new RefType(inner.deep_copy());
    }

    @Override
    public String toString() {
        return String.format("Ref(%s)", inner);
    }
}