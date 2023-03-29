public class base_class {
    private String name;

    public static int number;
    public base_class(String name){
        this.name =name;
    }
    public String toString()
    {
        return "base class: " + name + " " + number;
    }

    public static void  increment()
    {
        number += 1;
    }
}
