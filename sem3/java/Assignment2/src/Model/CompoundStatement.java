package Model;

public class CompoundStatement implements IStatement {
    IStatement first_statement;
    IStatement second_statement;

    @Override
    public String toString() {
        return "CompStmt{" +
                "first statement=" + first_statement +
                ", second statement=" + second_statement +
                '}';
    }

    public ProgramState execute(ProgramState state) throws MatchException{
        //TODO ==========
        return state;
    }
}
