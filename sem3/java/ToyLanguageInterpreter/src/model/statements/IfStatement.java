package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import exceptions.StatementExecutionException;
import model.ProgramState;
import model.adt.IStack;
import model.expressions.Expression;
import model.value.BoolValue;
import model.value.Value;

public class IfStatement implements IStatement {
    Expression expression;
    IStatement then_statement, else_statement;

    public IfStatement(Expression expression, IStatement then_statement, IStatement else_statement)
    {
        this.expression =expression;
        this.then_statement=then_statement;
        this.else_statement=else_statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        Value result =this.expression.evaluate(state.getSymbols_table(), state.getHeap());
        if(result instanceof BoolValue boolResult)
        {
            IStatement istatement;
            if(boolResult.get_value())
            {
                istatement =then_statement;
            }
            else {
                istatement =else_statement;
            }
            IStack<IStatement> stack = state.getExecution_stack();
            stack.push(istatement);
            state.setExecution_stack(stack);
            return state;
        }
        else
        {
            throw new StatementExecutionException("Please profile a boolean expression for the if statement.");

        }
    }

    @Override
    public IStatement deep_copy() {
        return new IfStatement(expression.deep_copy(), then_statement.deep_copy(), else_statement.deep_copy());
    }

    @Override
    public String toString() {
        return String.format("if(%s){%s}else{%s}", expression.toString(), then_statement.toString(), else_statement.toString());

    }
}
