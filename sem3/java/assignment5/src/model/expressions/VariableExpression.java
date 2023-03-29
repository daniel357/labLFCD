package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.value.Value;

public class VariableExpression implements Expression{
    String id;

    public VariableExpression(String id)
    {
        this.id = id;
    }

    @Override
    public Expression deep_copy() {
        return new VariableExpression(id);
    }

    @Override
    public Value evaluate(IDictionary<String, Value> sym_table, IHeap heap) throws ExpressionEvaluationException, ADTException {
        return sym_table.get(id);
    }

    @Override
    public String toString() {
        return "VariableExpression{" +
                "id='" + id + '\'' +
                '}';
    }
}
