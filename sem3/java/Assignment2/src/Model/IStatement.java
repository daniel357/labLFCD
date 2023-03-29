package Model;
import Exception.MyException;
public interface IStatement {

    ProgramState execute(ProgramState state) throws MyException;

}
