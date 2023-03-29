package repository;

import exceptions.ADTException;
import model.ProgramState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    List<ProgramState> get_program_list();
    void add_program(ProgramState program);
    void setProgramStates(List<ProgramState> programStates);
    ProgramState get_current_state();

    void logPrgStateExec() throws IOException, ADTException;
    void emptyLogFile() throws IOException;

}
