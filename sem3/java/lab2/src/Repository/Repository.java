package Repository;

import Model.IVehicle;

public class Repository implements Irepository {
    private IVehicle[] vehicles;
    private int size;

    public Repository(int max_size)
    {
        this.size =0;
        this.vehicles = new IVehicle[max_size];
    }
    public void add(IVehicle vehicle)
    {
        try {
            this.vehicles[this.size++] = vehicle;
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public IVehicle[] get_all()
    {
        return this.vehicles;
    }

}
