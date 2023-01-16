package org.hbrs.se1.ws22.uebung10_2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class myStackTest {

    myStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new myStack<>(4);
    }

    @AfterEach
    void tearDown() {
        stack = null;
    }

    @Test
    void getCapacity() {
        //initialer Test, ob capacity richtig gesetzt wurde
        assertEquals(4,stack.getCapacity());
    }

    @Test
    void getSize() {
        //initialer Test, ob size = 0 ist
        assertEquals(0,stack.getSize());
    }

    @Test
    void empty() {
        //intialer Test, ob stack leer ist (size = 0)
        assertTrue(stack.empty());
        assertFalse(stack.isFull());
    }

    @Test
    void push() {
        //Stack leer
        assertTrue(stack.empty());

        //hinzufügen von einem Element + Ueberpruefen, ob size = 1
        stack.push(0);
        assertEquals(1,stack.getSize());

        //hinzufügen von Elementen solange size < MAX -1 + Ueberpruefen, ob size = 3
        for(int i = 1; i < stack.getCapacity()-1; i++) {
            stack.push(i);
        }
        assertEquals(3,stack.getSize());

        //hinzufügen von weiterem Element + Ueberpruefen, ob size = 4 und isFull = true
        stack.push(4);
        assertEquals(4,stack.getSize());
        assertTrue(stack.isFull());

        //hinzufügen von weiterem Element + Ueberpruefen, ob Exception geworfen wurde + size = 4 ist
        assertThrows(IndexOutOfBoundsException.class,()->stack.push(5));
        assertEquals(4,stack.getSize());

    }

    @Test
    void pop() {
        //Stack leer
        assertTrue(stack.empty());

        //testen ob Excpetion geworfen, wenn pop bei leerem Stack ausgeführt
        assertThrows(NoSuchElementException.class,()->stack.pop());
        assertEquals(0,stack.getSize());

        //hinzufügen von Elementen um pop zu testen
        for(int i = 0; i < stack.getCapacity(); i++) {
            stack.push(i);
        }
        assertEquals(4,stack.getSize());
        assertTrue(stack.isFull());

        //ein Element vom Stack nehmen, isFull = false, size = 3
        stack.pop();
        assertFalse(stack.isFull());
        assertEquals(3,stack.getSize());

        //Elemente vom Stack nehmen, solange size > 1 + Ueberpruefen ob size = 1
        for (int i = 3; i > 1; i--) {
            stack.pop();
        }
        assertEquals(1,stack.getSize());

        //Element vom stack nehmen, size = 0, empty() = true
        stack.pop();
        assertEquals(0,stack.getSize());
        assertTrue(stack.empty());
    }
}