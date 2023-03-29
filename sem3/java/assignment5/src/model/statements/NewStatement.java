package model.statements;


import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import exceptions.StatementExecutionException;
import model.ProgramState;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.expressions.Expression;
import model.types.RefType;
import model.types.Type;
import model.value.RefValue;
import model.value.Value;

public class NewStatement implements IStatement{
    private final String variable_name;
    private final Expression expression;

    public NewStatement(String varName, Expression expression) {
        this.variable_name = varName;
        this.expression = expression;
    }
    /**
     * Example:
     * Ref int v; new(v,20);
     * Ref Ref int a; new(a,v);
     * print(v);print(a)
     * At the end of execution:  Heap={1->20, 2->(1,int)}, SymTable={v->(1,int), a->(2,Ref int)} and Out={(1,int),(2,Ref int)}*/
    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        IDictionary<String, Value> symTable = state.getSymTable();
        IHeap heap = state.getHeap();
        if (!symTable.contains_the_key(variable_name))
            throw new StatementExecutionException(String.format("%s not in symTable", variable_name));
        Value variable_value = symTable.lookUp(variable_name);
        if (!(variable_value.get_type() instanceof RefType))
            throw new StatementExecutionException(String.format("%s in not of RefType", variable_name));
        Value evaluated = expression.evaluate(symTable, heap);
        Type locationType = ((RefValue)variable_value).getLocationType();
        if (!locationType.equals(evaluated.get_type()))
            throw new StatementExecutionException(String.format("%s not of %s", variable_name, evaluated.get_type()));
        int newPosition = heap.add(evaluated);
        symTable.put(variable_name, new RefValue(newPosition, locationType));
        state.setSymTable(symTable);
        state.setHeap(heap);
        return null;
    }

    @Override
    public IStatement deep_copy() {
        return new NewStatement(variable_name, expression.deep_copy());
    }

    @Override
    public String toString() {
        return String.format("New(%s, %s)", variable_name, expression);
    }
}