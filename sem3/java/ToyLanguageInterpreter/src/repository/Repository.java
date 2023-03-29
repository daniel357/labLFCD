package repository;

import exceptions.ADTException;
import model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<ProgramState> programStates;
    private int current_position;

    private final String logFilePath;

    public Repository(ProgramState programState, String logFilePath)
    {
        this.logFilePath =logFilePath;
        this.programStates= new ArrayList<>();
        this.current_position =0;
        this.add_program(programState);
    }


    public int getCurrent_position() {
        return current_position;
    }

    public void setCurrent_position(int current_position) {
        this.current_position = current_position;
    }

    @Override
    public List<ProgramState> get_program_list() {
        return this.programStates;
    }

    @Override
    public ProgramState get_current_state() {
        return this.programStates.get(current_position);
    }

    @Override
    public void add_program(ProgramState program) {
        this.programStates.add(program);
    }

    @Override
    public void setProgramStates(List<ProgramState> programStates) {
        this.programStates = programStates;
    }

    @Override
    public void logPrgStateExec() throws IOException, ADTException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println(this.programStates.get(0).programStateToString());
        logFile.close();
    }


    @Override
    public void emptyLogFile() throws IOException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
        logFile.close();
    }
}
