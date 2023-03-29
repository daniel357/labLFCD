package model.value;

import model.types.Type;

public interface Value {
    Type get_type();
    Value deep_copy();
}
