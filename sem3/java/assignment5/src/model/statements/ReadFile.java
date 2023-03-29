package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import exceptions.StatementExecutionException;
import model.ProgramState;
import model.adt.IDictionary;
import model.expressions.Expression;
import model.types.*;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement{
    private final Expression expression;
    private final String varName;

    public ReadFile(Expression expression, String varName) {
        this.expression = expression;
        this.varName = varName;
    }
    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        IDictionary<String, Value> symTable = state.getSymTable();
        IDictionary<String, BufferedReader> fileTable = state.getFileTable();

        if (symTable.contains_the_key(varName)) {
            Value value = symTable.get(varName);
            if (value.get_type().equals(new IntType())) {
                value = expression.evaluate(symTable, state.getHeap());
                if (value.get_type().equals(new StringType())) {
                    StringValue castValue = (StringValue) value;
                    if (fileTable.contains_the_key(castValue.getValue())) {
                        BufferedReader br = fileTable.get(castValue.getValue());
                        try {
                            String line = br.readLine();
                            if (line == null)
                                line = "0";
                            symTable.put(varName, new IntValue(Integer.parseInt(line)));
                        } catch (IOException e) {
                            throw new StatementExecutionException(String.format("Could not read from file %s", castValue));
                        }
                    } else {
                        throw new StatementExecutionException(String.format("The file table does not contain %s", castValue));
                    }
                } else {
                    throw new StatementExecutionException(String.format("%s does not evaluate to StringType", value));
                }
            } else {
                throw new StatementExecutionException(String.format("%s is not of type IntType", value));
            }
        } else {
            throw new StatementExecutionException(String.format("%s is not present in the symTable.", varName));
        }
        return null;
    }

    @Override
    public IStatement deep_copy() {
        return new ReadFile(expression.deep_copy(), varName);
    }

    @Override
    public String toString() {
        return String.format("ReadFile(%s, %s)", expression.toString(), varName);
    }
}