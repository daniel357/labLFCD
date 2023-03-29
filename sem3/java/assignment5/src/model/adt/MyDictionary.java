package model.adt;

import exceptions.ADTException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class MyDictionary<T1, T2> implements IDictionary<T1,T2> {
    HashMap<T1,T2> my_dictionary;

    public MyDictionary()
    {
        this.my_dictionary = new HashMap<>();
    }

    @Override
    public boolean contains_the_key(T1 id) {
        return this.my_dictionary.containsKey(id);
    }


    @Override
    public T2 get(T1 id) throws ADTException {
        if(!this.contains_the_key(id))
        {
            throw new ADTException(id + "is not defined!");
        }
        return this.my_dictionary.get(id);
    }

    @Override
    public void put(T1 id, T2 value) {
        this.my_dictionary.put(id, value);
    }


    @Override
    public String toString() {
        return this.my_dictionary.toString();
    }
    @Override
    public Set<T1> keySet() {
        return my_dictionary.keySet();
    }
    @Override
    public T2 lookUp(T1 key) throws ADTException {
        if (!contains_the_key(key))
            throw new ADTException( key + " is not defined.");
        return this.my_dictionary.get(key);
    }

    @Override
    public void remove(T1 id) throws ADTException {
        if (!contains_the_key(id))
            throw new ADTException(id + " is not defined.");
        this.my_dictionary.remove(id);
    }

    @Override
    public Collection<T2> values() {
        return this.my_dictionary.values();
    }

    public HashMap<T1,T2> getContent(){return my_dictionary;}
}
