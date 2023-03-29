package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.types.IntType;
import model.value.IntValue;
import model.value.Value;

public class ArithmeticExpression implements Expression{
    Expression expression1;
    Expression expression2;
    char operation;

    public ArithmeticExpression(Expression expression1, Expression expression2, char operation)
    {
        this.expression1 =expression1;
        this.expression2 =expression2;
        this.operation =operation;
    }

    @Override
    public Expression deep_copy() {
        return new ArithmeticExpression(expression1.deep_copy(), expression2.deep_copy(), operation);
    }

    @Override
    public Value evaluate(IDictionary<String, Value> sym_table, IHeap heap) throws ExpressionEvaluationException, ADTException {
        Value value1 = this.expression1.evaluate(sym_table, heap);
        Value value2;
        if(value1.get_type().equals(new IntType()))
        {
            value2 = this.expression2.evaluate(sym_table, heap);
            if(value2.get_type().equals(new IntType()))
            {

                IntValue int1 = (IntValue) value1;
                IntValue int2 = (IntValue) value2;
                int n1 = int1.get_value();
                int n2 =int2.get_value();
                if(this.operation == '+')
                {
                    return new IntValue(n1+n2);
                }
                else if(this.operation == '-')
                {
                   return new IntValue(n1-n2);
                }
                else if(this.operation == '*')
                {
                    return new IntValue(n1*n2);
                }
                else if(this.operation == '/')
                {
                    if(n2 == 0)
                    {
                        throw new ExpressionEvaluationException("Division by zero not defined!");
                    }
                    else
                    {
                        return new IntValue(n1 / n2);
                    }
                }
            }
            else
            {
                throw new ExpressionEvaluationException("The second operand is not of type IntValue!");
            }

        }
        else
        {
            throw new ExpressionEvaluationException("The first operand is not of type IntValue");

        }
        return null;
    }

    @Override
    public String toString() {
        return "ArithmeticExpression{" +
                expression1 + operation  + expression2 +
                '}';
    }
}
