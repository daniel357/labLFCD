package model.adt;

import exceptions.ADTException;

import java.util.List;

public interface IStack<T> {
    T pop() throws ADTException;
    void push(T element);

    boolean is_empty();

    List<T> getReversed();
}
