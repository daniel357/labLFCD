package Repository;
import Exceptions.AnimalExceptions;
import Model.IAnimal;

public interface Irepository {
    void add_animal(IAnimal animal)throws AnimalExceptions;

    void delete_animal(int position) throws AnimalExceptions;
    IAnimal get(int position);
    IAnimal[] get_all_animals();
    int get_size();

}
