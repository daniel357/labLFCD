package exceptions;

public class ToyLanguageError  extends Exception{
    public ToyLanguageError()
    {
        super();
    }

    public ToyLanguageError(String message)
    {
        super(message);
    }
}
