package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import exceptions.StatementExecutionException;
import model.ProgramState;
import model.adt.IStack;
import model.expressions.Expression;
import model.types.BoolType;
import model.value.BoolValue;
import model.value.Value;

public class WhileStatement implements IStatement{
    private final Expression expression;
    private final IStatement statement;

    public WhileStatement(Expression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    //Example:  int v; v=4; (while (v>0) print(v);v=v-1);print(v)
    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        Value value = expression.evaluate(state.getSymbols_table(), state.getHeap());
        IStack<IStatement> stack = state.getExecution_stack();
        if (!value.get_type().equals(new BoolType()))
            throw new StatementExecutionException(String.format("%s is not of BoolType", value));
        BoolValue boolValue = (BoolValue) value;
        if (boolValue.get_value()) {
            stack.push(this);
            stack.push(statement);
        }
        return state;
    }

    @Override
    public IStatement deep_copy() {
        return new WhileStatement(expression.deep_copy(), statement.deep_copy());
    }

    @Override
    public String toString() {
        return String.format("while(%s){%s}", expression, statement);
    }
}