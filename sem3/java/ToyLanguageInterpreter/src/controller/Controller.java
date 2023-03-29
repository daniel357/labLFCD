package controller;

import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import exceptions.StatementExecutionException;
import model.ProgramState;
import model.adt.IHeap;
import model.adt.IStack;
import model.adt.MyHeap;
import model.statements.IStatement;
import model.value.RefValue;
import model.value.Value;
import repository.IRepository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    IRepository repo;
    boolean display_flag = false;

    public void setDisplay_flag(boolean value)
    {
        this.display_flag =value;
    }

    public Controller(IRepository repo)
    {
        this.repo=repo;
    }

    public ProgramState one_step(ProgramState state) throws StatementExecutionException, ADTException, ExpressionEvaluationException, IOException {
        IStack<IStatement> my_stack = state.getExecution_stack();
        if (my_stack.is_empty())
            throw new StatementExecutionException("Program stack is empty!");
        IStatement top_statement = my_stack.pop();
        state.setExecution_stack(my_stack);
        return top_statement.execute(state);
    }

    public void all_steps() throws StatementExecutionException, ADTException, ExpressionEvaluationException, IOException {
        ProgramState programState = this.repo.get_current_state();
        repo.logPrgStateExec();
        display();
        while (!programState.getExecution_stack().is_empty())
        {
            one_step(programState);
            repo.logPrgStateExec();

            programState.getHeap().setContent((HashMap<Integer, Value>) garbageCollector(
                    getAddressesFromSymbolTable(programState.getSymbols_table().getContent().values(), programState.getHeap().getContent().values()),
                    programState.getHeap().getContent()));
            display();
        }
    }

    private void display() {
        if (display_flag) {
            System.out.println(this.repo.get_current_state().toString());
        }
    }

    Map<Integer, Value> garbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap){
        /** transform dictionary to set, verify if the data from symbols table is also located in the heap
         * then save in a map */
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


//    private List<Integer> getAddressesFromSymbolTable(Collection<Value> symbolTableValues) {
//        /** filter to only get the RefValues, then
//         * .map to get the address according to the Value
//         * then collect in a list*/
//        return symbolTableValues.stream()
//                .filter(v ->v instanceof RefValue)
//                .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddress();})
//                .collect(Collectors.toList());
//    }

    //safe
    private List<Integer> getAddressesFromSymbolTable(Collection<Value> symbolTableValue, Collection<Value> heap) {
        return Stream.concat(
                heap.stream()
                        .filter(v -> v instanceof RefValue)
                        .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddress();}),
                symbolTableValue.stream()
                        .filter(v -> v instanceof RefValue)
                        .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddress();})
        ).collect(Collectors.toList());
    }
}
