package model.statements;

import model.ProgramState;
import model.adt.IStack;

public class CompoundStatement implements IStatement {
    IStatement first_statement;
    IStatement second_statement;

    public CompoundStatement(IStatement first_statement, IStatement second_statement)
    {
        this.first_statement = first_statement;
        this.second_statement =second_statement;
    }

    @Override
    public ProgramState execute(ProgramState state)
    {
        IStack<IStatement> my_stack = state.getExecution_stack();
        my_stack.push(second_statement);
        my_stack.push(first_statement);
        state.setExecution_stack(my_stack);
        return state;
    }

    public IStatement deep_copy()
    {
        return new CompoundStatement(first_statement.deep_copy(), second_statement.deep_copy());
    }

    @Override
    public String toString() {
        return "CompoundStatement{" +
                "first_statement=" + first_statement +
                ", second_statement=" + second_statement +
                '}';
    }

}
