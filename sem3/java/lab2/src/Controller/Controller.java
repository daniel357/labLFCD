package Controller;

import Model.IVehicle;
import Repository.Irepository;

public class Controller {
    private Irepository repo;

    public Controller(Irepository irepository)
    {
        this.repo =irepository;
    }

    public void add(IVehicle vehicle)
    {
        this.repo.add(vehicle);
    }

    public void print_all(String color)
    {
        IVehicle[] vehicles = this.repo.get_all();
        for (IVehicle v:vehicles) {
            if(v.solve(color))
                System.out.println(v.to_string());
        }
    }


}
