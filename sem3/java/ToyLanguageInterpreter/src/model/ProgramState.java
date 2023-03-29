package model;

import exceptions.ADTException;
import model.adt.IHeap;
import model.statements.IStatement;
import model.adt.IDictionary;
import model.adt.IList;
import model.adt.IStack;
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

    public ProgramState(IStack<IStatement> stack, IDictionary<String, Value> symbols_table, IList<Value> out,IDictionary<String, BufferedReader> fileTable,IHeap heap, IStatement program)
    {
        this.execution_stack =stack;
        this.out =out;
        this.symbols_table = symbols_table;
        this.original_program =program.deep_copy();
        this.fileTable = fileTable;
        this.execution_stack.push(this.original_program);
        this.heap =heap;
    }

    public void setExecution_stack(IStack<IStatement> execution_stack) {
        this.execution_stack = execution_stack;
    }

    public void setSymbols_table(IDictionary<String, Value> symbols_table) {
        this.symbols_table = symbols_table;
    }

    public void setOut(IList<Value> out) {
        this.out = out;
    }

    public IStack<IStatement> getExecution_stack() {
        return execution_stack;
    }

    public IDictionary<String, Value> getSymbols_table() {
        return symbols_table;
    }

    public void setFileTable(IDictionary<String, BufferedReader> newFileTable) {
        this.fileTable = newFileTable;
    }

    public IList<Value> getOut() {
        return out;
    }


    public void setHeap(IHeap newHeap) {
        this.heap = newHeap;
    }

    public IHeap getHeap() {
        return heap;
    }

//    @Override
//    public String toString() {
//        return "Execution stack: \n" + execution_stack.getReversed() + "\nSymbol table: \n" + symbols_table.toString() + "\nOutput list: \n" + out.toString() + "\nFile table:\n" + heap.toString() + "\n";
//    }

    public IDictionary<String, BufferedReader> getFileTable() {
        return fileTable;
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
            symTableStringBuilder.append(String.format("%s -> %s\n", key, symbols_table.get(key).toString()));
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
        return "Execution stack: \n" + execution_stack.getReversed() + "\nSymbol table: \n" + symbols_table.toString() + "\nOutput list: \n" + out.toString() + "\nFile table:\n" + fileTable.toString() + "\nHeap memory:\n" + heap.toString() + "\n";
    }
    public String programStateToString() throws ADTException {
        return "Execution stack: \n" + exeStackToString() + "Symbol table: \n" + symTableToString() + "Output list: \n" + outToString() + "File table:\n" + fileTableToString() + "Heap memory:\n" + heapToString();
    }
//    public String programStateToString() throws ADTException {
//        return "Execution stack: \n" + exeStackToString() + "Symbol table: \n" + symTableToString() + "Output list: \n" + outToString() + "File table:\n" + fileTableToString();
//    }

}
