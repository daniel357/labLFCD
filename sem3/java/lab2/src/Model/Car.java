package Model;

public class  Car implements IVehicle {
    private String color;
    public Car(String color)
    {
        this.color=color;
    }
    public String to_string()
    {
        return " Car, color= "+this.color;
    }

    public boolean solve(String color)
    {
        if(this.color.compareTo(color) == 0)
            return true;
        else
            return false;
    }
}
