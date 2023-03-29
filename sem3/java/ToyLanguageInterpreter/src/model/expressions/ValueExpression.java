package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.value.Value;

public class ValueExpression implements Expression{
    Value value;

    public ValueExpression(Value value)
    {
        this.value =value;
    }

    @Override
    public Expression deep_copy() {
        return new ValueExpression(value);
    }

    @Override
    public Value evaluate(IDictionary<String, Value> sym_table, IHeap heap) throws ExpressionEvaluationException, ADTException {
        return this.value;
    }

    @Override
    public String toString() {
        return "ValueExpression{" +
                "value=" + value +
                '}';
    }
}
