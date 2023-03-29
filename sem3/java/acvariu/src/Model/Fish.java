package Model;

public class Fish implements IAnimal{
    private int age;
    String species;

    public Fish(int age, String species) {
        this.age = age;
        this.species = species;
    }

    @Override
    public int get_age() {
        return age;
    }

    @Override
    public String toString() {
        return "fish{" +
                "age=" + age +
                ", species=" + species +
                '}';
    }
}
