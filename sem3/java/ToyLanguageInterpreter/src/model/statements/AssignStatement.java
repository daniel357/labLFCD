package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import exceptions.StatementExecutionException;
import model.ProgramState;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.expressions.Expression;
import model.types.Type;
import model.value.Value;

public class AssignStatement implements IStatement {
    private String id;
    private Expression expression;

    public AssignStatement(String id, Expression expression)
    {
        this.expression=expression;
        this.id=id;
    }

    @Override
    public IStatement deep_copy() {
        return new AssignStatement(id, expression.deep_copy());
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        IDictionary<String, Value> the_table = state.getSymbols_table();
        IHeap heap = state.getHeap();
        if(the_table.contains_the_key(id))
        {
            Value value = expression.evaluate(the_table, heap);
            Type type_id = the_table.get(id).get_type();
            if(value.get_type().equals(type_id))
            {
                the_table.put(id, value);
            }
            else
            {
                throw new StatementExecutionException("Declared type of the variable " + id + " and the type of the expression do not match.");

            }
        }
        else
        {
            throw new StatementExecutionException("The variable " + id +" was not previously declared");
        }
        state.setSymbols_table(the_table);
        return state;
    }

    @Override
    public String toString() {
        return "AssignStatement{" +
                "id='" + id + '\'' +
                ", expression=" + expression +
                '}';
    }
}
