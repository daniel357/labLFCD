package model.adt;

import exceptions.ADTException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MyList<T> implements IList<T> {
    List<T> my_list;

    public MyList()
    {
        this.my_list =new ArrayList<>();
    }

    @Override
    public boolean is_empty() {
        return this.my_list.isEmpty();
    }

    @Override
    public List<T> get_list() {
        return my_list;
    }

    @Override
    public T pop() throws ADTException {
        if (my_list.isEmpty())
        {
            throw new ADTException("The list is empty!");
        }
        return this.my_list.remove(0);
    }

    @Override
    public void add(T element) {
        this.my_list.add(element);
    }

    @Override
    public String toString() {
        return "MyList{" +
                "my_list=" + my_list +
                '}';
    }
}
