package model.expressions;


import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.value.RefValue;
import model.value.Value;

public class ReadHeapExpression implements Expression{
    private final Expression expression;

    public ReadHeapExpression(Expression expression) {
        this.expression = expression;
    }
    @Override
    public Value evaluate(IDictionary<String, Value> symTable, IHeap heap) throws ExpressionEvaluationException, ADTException {
        Value value = expression.evaluate(symTable, heap);
        if (!(value instanceof RefValue))
            throw new ExpressionEvaluationException(String.format("%s not of RefType", value));
        RefValue refValue = (RefValue) value;
        return heap.get(refValue.getAddress());
    }

    @Override
    public Expression deep_copy() {
        return new ReadHeapExpression(expression.deep_copy());
    }

    @Override
    public String toString() {
        return String.format("ReadHeap(%s)", expression);
    }
}