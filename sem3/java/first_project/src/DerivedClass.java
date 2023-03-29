public class DerivedClass extends base_class{
    public DerivedClass(String name) {
        super(name);
        int i=0;
    }

    @Override
    public String toString()
    {
        return "derived class" + super.toString();
    }
}
