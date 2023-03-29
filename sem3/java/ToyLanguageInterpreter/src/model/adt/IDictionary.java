package model.adt;

import exceptions.ADTException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public interface IDictionary<T1,T2> {
    void put(T1 value1, T2 value2);
    T2 get(T1 id) throws ADTException;
    boolean contains_the_key(T1 id);

    Set<T1> keySet();
    T2 lookUp(T1 key) throws ADTException;

    void remove(T1 id) throws ADTException;

     Collection<T2> values();

    public HashMap<T1,T2> getContent();
}