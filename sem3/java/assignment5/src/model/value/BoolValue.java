package model.value;

import model.types.BoolType;
import model.types.Type;

public class BoolValue implements Value{
   private boolean value;

   public BoolValue(boolean value)
   {
       this.value = value;
   }

   @Override
    public Type get_type()
   {
       return new BoolType();
   }

    @Override
    public String toString() {
        return "BoolValue{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof BoolValue))
            return false;
        BoolValue casted_object = (BoolValue) obj;
        return value ==casted_object.value;
    }

    public boolean get_value()
    {
        return this.value;
    }

    @Override
    public Value deep_copy() {
        return new BoolValue(value);
    }
}
