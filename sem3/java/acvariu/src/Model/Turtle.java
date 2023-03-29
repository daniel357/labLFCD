package Model;

public class Turtle implements IAnimal{
    int age;
    int weight;
    public Turtle(int age, int weight) {
        this.age = age;
        this.weight = weight;
    }

    @Override
    public int get_age() {
        return age;
    }

    @Override
    public String toString() {
        return "Turtle{" +
                "age=" + age +
                ", weight=" + weight +
                '}';
    }
}
