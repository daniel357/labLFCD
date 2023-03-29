package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import exceptions.StatementExecutionException;
import model.ProgramState;
import model.adt.IDictionary;
import model.types.Type;
import model.value.Value;

public class VariableDeclarationStatement implements IStatement {
    String name;
    Type type;

    public VariableDeclarationStatement(String name, Type type)
    {
        this.name = name;
        this.type =type;
    }

    @Override
    public IStatement deep_copy() {
        return new VariableDeclarationStatement(name, type.deep_copy());
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        IDictionary<String, Value> data_table = state.getSymbols_table();
        if(data_table.contains_the_key(name))
        {
            throw new StatementExecutionException("Variable " + name+ " already exists in the symbols table");
        }
        data_table.put(name, type.get_default());
        state.setSymbols_table(data_table);
        return state;
    }

    @Override
    public String toString() {
        return "VariableDeclarationStatement{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
