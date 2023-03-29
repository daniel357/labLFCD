package model.statements;

import exceptions.*;
import model.ProgramState;
public interface IStatement {
    ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException,ADTException;
    IStatement deep_copy();
}
