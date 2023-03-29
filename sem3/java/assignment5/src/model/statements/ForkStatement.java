package model.statements;


import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import exceptions.StatementExecutionException;
import model.ProgramState;
import model.adt.IDictionary;
import model.adt.IStack;
import model.adt.MyDictionary;
import model.adt.MyStack;
import model.value.Value;

import java.util.Map;

public class ForkStatement implements IStatement{
    private final IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }
    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        IStack<IStatement> newStack = new MyStack<>();
        newStack.push(statement);
        IDictionary<String, Value> newSymTable = new MyDictionary<>();
        for (Map.Entry<String, Value> entry: state.getSymTable().getContent().entrySet()) {
            newSymTable.put(entry.getKey(), entry.getValue().deep_copy());
        }

        return new ProgramState(newStack, newSymTable, state.getOut(), state.getFileTable(), state.getHeap());
    }


    @Override
    public IStatement deep_copy() {
        return new ForkStatement(statement.deep_copy());
    }

    @Override
    public String toString() {
        return String.format("Fork(%s)", statement.toString());
    }
}