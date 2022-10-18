package org.hbrs.se1.ws22.uebung2.test;

import org.hbrs.se1.ws22.uebung2.ConcreteMember;
import org.hbrs.se1.ws22.uebung2.Container;
import org.hbrs.se1.ws22.uebung2.ContainerException;
import org.hbrs.se1.ws22.uebung2.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    Container container = new Container();
    Member member1 = new ConcreteMember(1);
    Member member2 = new ConcreteMember(2);
    Member member3 = new ConcreteMember(3);

    @BeforeEach
    void setUp() throws ContainerException {
        container.addMember(member1);
        container.addMember(member2);
        container.addMember(member3);
    }

    @AfterEach
    void tearDown() {
        container.deleteMember(3);
        container.deleteMember(2);
        container.deleteMember(1);
    }

    @Test
    void containsMember() {
        Member member4 = new ConcreteMember(4);
        assertTrue(container.containsMember(member1));
        assertFalse(container.containsMember(member4));
    }

    @Test
    void addMember() throws ContainerException {
        assertEquals(3, container.size());
        assertThrows(ContainerException.class, () -> container.addMember(member1));
        assertThrows(NullPointerException.class, () -> container.addMember(null));
        assertEquals(3,container.size());
    }

    @Test
    void deleteMember() {
        container.deleteMember(3);
        assertEquals(2,container.size());
        assertEquals("Es gibt kein Member-Objekt mit der ID 3", container.deleteMember(3));
        assertEquals("Es gibt kein Member-Objekt mit der ID null", container.deleteMember(null));
    }

    @Test
    void size() {
        assertEquals(3,container.size());
    }

    @Test
    void dump() {

    }
}