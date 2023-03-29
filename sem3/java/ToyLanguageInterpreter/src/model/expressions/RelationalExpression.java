package model.expressions;


import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import model.adt.IHeap;
import model.types.IntType;
import model.adt.IDictionary;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.Value;

import java.util.Objects;

public class RelationalExpression implements Expression{
    Expression expression1;
    Expression expression2;
    String operator;

    public RelationalExpression(String operator, Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> symTable, IHeap heap) throws ExpressionEvaluationException, ADTException {
        Value value1, value2;
        value1 = this.expression1.evaluate(symTable, heap);
        if (value1.get_type().equals(new IntType())) {
            value2 = this.expression2.evaluate(symTable, heap);
            if (value2.get_type().equals(new IntType())) {
                IntValue val1 = (IntValue) value1;
                IntValue val2 = (IntValue) value2;
                int v1, v2;
                v1 = val1.get_value();
                v2 = val2.get_value();
                if (Objects.equals(this.operator, "<"))
                    return new BoolValue(v1 < v2);
                else if (Objects.equals(this.operator, "<="))
                    return new BoolValue(v1 <= v2);
                else if (Objects.equals(this.operator, "=="))
                    return new BoolValue(v1 == v2);
                else if (Objects.equals(this.operator, "!="))
                    return new BoolValue(v1 != v2);
                else if (Objects.equals(this.operator, ">"))
                    return new BoolValue(v1 > v2);
                else if (Objects.equals(this.operator, ">="))
                    return new BoolValue(v1 >= v2);
            } else
                throw new ExpressionEvaluationException("Second operand is not an integer.");
        } else
            throw new ExpressionEvaluationException("First operand in not an integer.");
        return null;
    }

    @Override
    public Expression deep_copy() {
        return new RelationalExpression(operator, expression1.deep_copy(), expression2.deep_copy());
    }

    @Override
    public String toString() {
        return this.expression1.toString() + " " + this.operator + " " + this.expression2.toString();
    }
}