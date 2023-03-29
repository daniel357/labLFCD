package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.value.Value;

public interface Expression {
    Value evaluate(IDictionary<String, Value> sym_table, IHeap heap) throws ExpressionEvaluationException, ADTException;
    Expression deep_copy();
}