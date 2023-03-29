package Model;

public class Bike implements IVehicle{
    private String color;

    public Bike(String color)
    {
        this.color=color;
    }


    public String to_string() {
        return "Bike{" +
                "color='" + color + '\'' +
                '}';
    }

    public boolean solve(String color)
    {
        if(this.color.compareTo(color)==0)
            return true;
        else
            return false;
    }
}
