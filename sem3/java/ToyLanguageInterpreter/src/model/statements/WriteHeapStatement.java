package model.statements;


import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import exceptions.StatementExecutionException;
import model.ProgramState;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.expressions.Expression;
import model.value.RefValue;
import model.value.Value;

public class WriteHeapStatement implements IStatement{
    private final String variable_name;
    private final Expression expression;

    public WriteHeapStatement(String varName, Expression expression) {
        this.variable_name = varName;
        this.expression = expression;
    }
    /**
     * Example:
     * Ref int v;new(v,20);
     * Ref Ref int a; new(a,v);
     * print(rH(v));
     * print(rH(rH(a))+5)
     * At the end of execution:  Heap={1->20, 2->(1,int)}, SymTable={v->(1,int), a->(2,Ref int)} and Out={20, 25} */
    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        IDictionary<String, Value> symTable = state.getSymbols_table();
        IHeap heap = state.getHeap();
        if (!symTable.contains_the_key(variable_name))
            throw new StatementExecutionException(String.format("%s not present in the symTable", variable_name));
        Value value = symTable.lookUp(variable_name);
        if (!(value instanceof RefValue))
            throw new StatementExecutionException(String.format("%s not of RefType", value));
        RefValue refValue = (RefValue) value;
        Value evaluated = expression.evaluate(symTable, heap);
        if (!evaluated.get_type().equals(refValue.getLocationType()))
            throw new StatementExecutionException(String.format("%s not of %s", evaluated, refValue.getLocationType()));
        heap.update(refValue.getAddress(), evaluated);
        state.setHeap(heap);
        return state;
    }

    @Override
    public IStatement deep_copy() {
        return new WriteHeapStatement(variable_name, expression.deep_copy());
    }

    @Override
    public String toString() {
        return String.format("WriteHeap(%s, %s)", variable_name, expression);
    }
}