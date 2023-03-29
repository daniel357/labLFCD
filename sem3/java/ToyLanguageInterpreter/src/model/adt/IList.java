package model.adt;

import exceptions.ADTException;

import java.util.List;

import java.util.function.Consumer;

public interface IList<T> {

    void add(T element);

    T pop() throws ADTException;

    boolean is_empty();

    List<T> get_list();
}
