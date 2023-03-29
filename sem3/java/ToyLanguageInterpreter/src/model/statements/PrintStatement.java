package model.statements;


import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import exceptions.StatementExecutionException;
import model.ProgramState;
import model.adt.IList;
import model.expressions.Expression;
import model.value.Value;

public class PrintStatement implements IStatement {
    Expression expression;

    public PrintStatement(Expression expression)
    {
        this.expression =expression;
    }

    @Override
    public IStatement deep_copy() {
        return new PrintStatement(expression.deep_copy());
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        IList<Value> out_table = state.getOut();
        out_table.add(expression.evaluate(state.getSymbols_table(), state.getHeap()));
        state.setOut(out_table);
        return state;
    }

    @Override
    public String toString() {
        return "PrintStatement{" +
                "expression=" + expression +
                '}';
    }
}
