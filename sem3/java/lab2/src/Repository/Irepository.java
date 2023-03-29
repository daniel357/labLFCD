package Repository;

import Model.IVehicle;

public interface Irepository {

    public void add(IVehicle vehicle);
    public IVehicle[] get_all();
}
