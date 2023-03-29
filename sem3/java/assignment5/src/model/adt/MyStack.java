package model.adt;

import exceptions.ADTException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MyStack<T> implements  IStack<T>{
    Stack<T> my_stack;

    public MyStack()
    {
        this.my_stack =new Stack<>();
    }

    @Override
    public T pop() throws ADTException
    {
        if (my_stack.isEmpty())
            throw new ADTException("The Stack is empty!");
        return this.my_stack.pop();
    }


    @Override
    public String toString() {
        return "MyStack{" +
                "my_stack=" + my_stack +
                '}';
    }

    @Override
    public void push(T element)
    {
        this.my_stack.push(element);
    }

    @Override
    public boolean is_empty()
    {
        return this.my_stack.isEmpty();
    }


    @Override
    public List<T> getReversed() {
        List<T> list = Arrays.asList((T[]) my_stack.toArray());
        Collections.reverse(list);
        return list;
    }
}
