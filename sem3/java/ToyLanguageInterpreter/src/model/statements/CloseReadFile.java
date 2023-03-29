package model.statements;



import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import exceptions.StatementExecutionException;
import model.adt.IHeap;
import model.expressions.Expression;
import model.ProgramState;
import model.types.StringType;
import model.adt.IDictionary;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFile implements IStatement{
    private final Expression expression;

    public CloseReadFile(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        Value value = expression.evaluate(state.getSymbols_table(), state.getHeap());
        if (!value.get_type().equals(new StringType()))
            throw new StatementExecutionException(String.format("%s does not evaluate to StringValue", expression));
        StringValue fileName = (StringValue) value;
        IDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if (!fileTable.contains_the_key(fileName.getValue()))
            throw new StatementExecutionException(String.format("%s is not present in the FileTable", value));
        BufferedReader br = fileTable.get(fileName.getValue());
        try {
            br.close();
        } catch (IOException e) {
            throw new StatementExecutionException(String.format("Unexpected error in closing %s", value));
        }
        fileTable.remove(fileName.getValue());
        state.setFileTable(fileTable);
        return null;
    }

    @Override
    public IStatement deep_copy() {
        return new CloseReadFile(expression.deep_copy());
    }

    @Override
    public String toString() {
        return String.format("CloseReadFile(%s)", expression.toString());
    }
}