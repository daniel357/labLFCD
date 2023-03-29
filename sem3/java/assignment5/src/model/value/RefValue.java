package model.value;


import model.types.RefType;
import model.types.Type;

public class RefValue implements Value{
    private final int address;
    private final Type locationType;

    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }
    @Override
    public Type get_type() {
        return new RefType(locationType);
    }

    public int getAddress() {
        return address;
    }

    public Type getLocationType() {
        return locationType;
    }

    @Override
    public Value deep_copy() {
        return new RefValue(address, locationType.deep_copy());
    }

    @Override
    public String toString() {
        return String.format("(%d, %s)", address, locationType);
    }
}