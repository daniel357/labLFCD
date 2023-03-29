package Controller;

import Exceptions.AnimalExceptions;
import Model.IAnimal;
import Repository.Irepository;

public class Controller {
    private Irepository repo;

    public Controller(Irepository repo)
    {
        this.repo=repo;
    }

    public void add_animal(IAnimal animal) throws AnimalExceptions
    {
        this.repo.add_animal(animal);
    }

    public void delete_animal(int position) throws AnimalExceptions
    {
        this.repo.delete_animal(position);
    }

    public IAnimal[] filter_by_age()
    {
        IAnimal[] animals_filtered = new IAnimal[100];
        int size=0;
        for (int i = 0; i < this.repo.get_size(); i++) {
            if(this.repo.get_all_animals()[i].get_age()>=2)
            {
                animals_filtered[size++] = this.repo.get_all_animals()[i];
            }
        }
        return animals_filtered;
    }
}
