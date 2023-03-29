package View;

import Controller.Controller;
import Model.Bike;
import Model.Car;
import Model.IVehicle;

import Repository.Irepository;
import Repository.Repository;

public class Main {

    public static void main(String[] args){
        IVehicle vehicle1 = new Car("purple");
        IVehicle vehicle2 = new Bike("red");
        IVehicle vehicle3 = new Car("red");
        IVehicle vehicle4 = new Car("black");

        Irepository repo = new Repository(3);
        Controller controller = new Controller(repo);
        controller.add(vehicle1);
        controller.add(vehicle2);
        controller.add(vehicle3);
        controller.print_all("red");
    }
}