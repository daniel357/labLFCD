package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.types.BoolType;
import model.value.BoolValue;
import model.value.Value;

import java.util.Objects;

public class LogicExpression implements Expression{
    Expression expression1, expression2;
    String operation;

    public LogicExpression(Expression expression1, Expression expression2, String operation)
    {
        this.expression1=expression1;
        this.expression2=expression2;
        this.operation=operation;
    }

    @Override
    public Expression deep_copy() {
        return new LogicExpression(expression1.deep_copy(), expression2.deep_copy(), operation);
    }

    @Override
    public Value evaluate(IDictionary<String, Value> sym_table, IHeap heap) throws ExpressionEvaluationException, ADTException {
        Value value1, value2;
        value1 =this.expression1.evaluate(sym_table, heap);
        if(value1.get_type().equals(new BoolType()))
        {
            value2 =this.expression2.evaluate(sym_table, heap);
            if(value2.get_type().equals(new BoolType()))
            {
                BoolValue boolValue1= (BoolValue) value1;
                BoolValue boolValue2 = (BoolValue) value2;

                boolean bool1,bool2;
                bool1=boolValue1.get_value();
                bool2=boolValue2.get_value();
                if(Objects.equals(this.operation, "and"))
                {
                    return new BoolValue(bool1 && bool2);
                }
                else if(Objects.equals(this.operation, "or"))
                {
                    return new BoolValue(bool1 || bool2);
                }
            }
            else
            {
                throw new ExpressionEvaluationException("Second operand is not a boolean!");
            }
        }
        else {
            throw new ExpressionEvaluationException("First operand is not a boolean!");
        }
        return null;
    }
    @Override
    public String toString() {
        return expression1.toString() + " " + operation + " " + expression2.toString();
    }
}
