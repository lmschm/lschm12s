package org.hbrs.se1.ws22.uebung10_2;

import java.util.NoSuchElementException;
import java.util.Stack;

public class myStack<T> extends Stack<T> {

    private int capacity;        //maximale Füllgröße
    private int size;           //anzahl der im Stack liegenden Objekte

    private T[] array;          //Speicher

    public myStack(int maxSize) {
        array = (T[])new Object[maxSize];
        this.capacity = maxSize;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean empty() {
        if(getSize() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() {
        if(getSize() == getCapacity()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T push(T object) {

        if (getSize() == getCapacity()) throw new IndexOutOfBoundsException("Die maximale Groesse des Stacks ist erreicht");
        size++;
        for (int j = 0; j < getCapacity(); j++) {
            if (this.array[j] == null) {
                this.array[j] = object;
                break;
            } else {
                continue;
            }
        }

        return  object;

    }

    @Override
    public T pop() {
        if (getSize() < 1) throw new NoSuchElementException("Dieser Stack ist leer");
        T lastDigit = null;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] != null) {
                lastDigit = array[i];
                array[i] = null;
                size--;
                break;
            }
        }
        return lastDigit;
    }
}
