package model;

import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import exceptions.StatementExecutionException;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.adt.IList;
import model.adt.IStack;
import model.statements.IStatement;
import model.value.Value;

import java.io.BufferedReader;
import java.util.List;

public class ProgramState {
    private IStack<IStatement> execution_stack;
    private IDictionary<String, Value> symbols_table;
    private IList<Value> out;

    private IDictionary<String, BufferedReader> fileTable;

    private IHeap heap;
    private IStatement original_program;

    private int id;
    private static int lastId = 0;

    public ProgramState(IStack<IStatement> stack, IDictionary<String,Value> symTable, IList<Value> out, IDictionary<String, BufferedReader> fileTable, IHeap heap, IStatement program) {
        this.execution_stack = stack;
        this.symbols_table = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.original_program = program.deep_copy();
        this.execution_stack.push(this.original_program);
        this.id = setId();
    }

    public ProgramState(IStack<IStatement> stack, IDictionary<String,Value> symTable, IList<Value> out, IDictionary<String, BufferedReader> fileTable, IHeap heap) {
        this.execution_stack = stack;
        this.symbols_table = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.id = setId();
    }

    public synchronized int setId() {
        lastId++;
        return lastId;
    }

    public void setExeStack(IStack<IStatement> newStack) {
        this.execution_stack = newStack;
    }

    public void setSymTable(IDictionary<String, Value> newSymTable) {
        this.symbols_table = newSymTable;
    }

    public void setOut(IList<Value> newOut) {
        this.out = newOut;
    }

    public void setFileTable(IDictionary<String, BufferedReader> newFileTable) {
        this.fileTable = newFileTable;
    }

    public void setHeap(IHeap newHeap) {
        this.heap = newHeap;
    }

    public IStack<IStatement> getExeStack() {
        return execution_stack;
    }

    public IDictionary<String, Value> getSymTable() {
        return symbols_table;
    }

    public IList<Value> getOut() {
        return out;
    }

    public IDictionary<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public IHeap getHeap() {
        return heap;
    }

    public boolean isNotCompleted() {
        return execution_stack.is_empty();
    }

    public ProgramState oneStep() throws StatementExecutionException, ADTException, ExpressionEvaluationException {
        if (execution_stack.is_empty())
            throw new StatementExecutionException("Program state stack is empty!");
        IStatement currentStatement = execution_stack.pop();
        return currentStatement.execute(this);
    }

    public String exeStackToString() {
        StringBuilder exeStackStringBuilder = new StringBuilder();
        List<IStatement> stack = execution_stack.getReversed();
        for (IStatement statement: stack) {
            exeStackStringBuilder.append(statement.toString()).append("\n");
        }
        return exeStackStringBuilder.toString();
    }

    public String symTableToString() throws ADTException {
        StringBuilder symTableStringBuilder = new StringBuilder();
        for (String key: symbols_table.keySet()) {
            symTableStringBuilder.append(String.format("%s -> %s\n", key, symbols_table.lookUp(key).toString()));
        }
        return symTableStringBuilder.toString();
    }

    public String outToString() {
        StringBuilder outStringBuilder = new StringBuilder();
        for (Value elem: out.get_list()) {
            outStringBuilder.append(String.format("%s\n", elem.toString()));
        }
        return outStringBuilder.toString();
    }

    public String fileTableToString() {
        StringBuilder fileTableStringBuilder = new StringBuilder();
        for (String key: fileTable.keySet()) {
            fileTableStringBuilder.append(String.format("%s\n", key));
        }
        return fileTableStringBuilder.toString();
    }

    public String heapToString() throws ADTException {
        StringBuilder heapStringBuilder = new StringBuilder();
        for (int key: heap.keySet()) {
            heapStringBuilder.append(String.format("%d -> %s\n", key, heap.get(key)));
        }
        return heapStringBuilder.toString();
    }

    @Override
    public String toString() {
        return "Id: " + id + "\nExecution stack: \n" + execution_stack.getReversed() + "\nSymbol table: \n" + symbols_table.toString() + "\nOutput list: \n" + out.toString() + "\nFile table:\n" + fileTable.toString() + "\nHeap memory:\n" + heap.toString() + "\n";
    }

    public String programStateToString() throws ADTException {
        return "Id: " + id + "\nExecution stack: \n" + exeStackToString() + "Symbol table: \n" + symTableToString() + "Output list: \n" + outToString() + "File table:\n" + fileTableToString() + "Heap memory:\n" + heapToString();
    }

}
