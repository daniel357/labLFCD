package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import exceptions.StatementExecutionException;
import model.ProgramState;
import model.adt.*;
import model.expressions.Expression;
import model.types.StringType;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFile implements IStatement{
    private final Expression expression;

    public OpenReadFile(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        //
        Value value = expression.evaluate(state.getSymTable(),state.getHeap());
        if (value.get_type().equals(new StringType())) {
            StringValue fileName = (StringValue) value;
            IDictionary<String, BufferedReader> fileTable = state.getFileTable();
            if (!fileTable.contains_the_key(fileName.getValue())) {//check if the filename already exists in the file table dictionary
                BufferedReader br;
                try {
                    br = new BufferedReader(new FileReader(fileName.getValue()));//trying to open the file, if it exists
                } catch (FileNotFoundException e) {
                    throw new StatementExecutionException(String.format("%s could not be opened", fileName.getValue()));
                }
                fileTable.put(fileName.getValue(), br);//add the file name to the file table if opened successfully
                state.setFileTable(fileTable);
            } else {
                throw new StatementExecutionException(String.format("%s is already opened", fileName.getValue()));
            }
        } else {
            throw new StatementExecutionException(String.format("%s does not evaluate to StringType", expression.toString()));
        }
        return null;
    }

    @Override
    public IStatement deep_copy() {
        return new OpenReadFile(expression.deep_copy());
    }

    @Override
    public String toString() {
        return String.format("OpenReadFile(%s)", expression.toString());
    }
}