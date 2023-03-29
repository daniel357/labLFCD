package model.types;

import model.value.Value;

public interface Type {
    boolean equals(Type another_type);
    Value get_default();

    Type deep_copy();
}
