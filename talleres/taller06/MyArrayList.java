import java.io.*;
public class MyArrayList<T>
{
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private T elements[];

    public MyArrayList(){
        this.elements = (T[])new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    int size(){
        return size;
    }

    void add(int index, T e) throws Exception{
        if(index < 0 || index > size){throw new Exception("Index out of bounds " + index);}
        if(size == elements.length){
            T[] temps = (T[])new Object[elements.length * 2];
            for(int i = 0; i < size; i++){
                temps[i] = elements[i];
            }
            elements = temps;
        }          
        for(int i = size; i > index; i--){
            elements[i] = elements[i - 1];
        }
        elements[index] = e;
        size++;
    }

    T get(int n) throws Exception{
        if(n < 0 || n >= size){throw new Exception("Index out of bounds " + n);}
        return elements[n];
    }
}
