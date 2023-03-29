package Repository;

import Exceptions.AnimalExceptions;
import Model.IAnimal;

public class Repository implements Irepository {
    IAnimal[] animals;
    int size;

    public Repository(int max_size)
    {
        this.animals = new IAnimal[max_size];
    }
    @Override
    public int get_size() {
        return size;
    }

    public void add_animal(IAnimal animal) throws AnimalExceptions {
        if(size == animals.length)
            throw new AnimalExceptions("capacity exceeded");
        animals[size++] = animal;
    }

    public IAnimal get(int position)
    {
        return animals[position];
    }
    public void delete_animal(int position) throws AnimalExceptions {
        if(position<0 || position >this.animals.length)
            throw new AnimalExceptions("position not located");
        for (int i = position; i < size-1; i++) {
            this.animals[i] = this.animals[i+1];
        }
        size--;
    }
    public IAnimal[] get_all_animals()
    {
        return animals;
    }

}
