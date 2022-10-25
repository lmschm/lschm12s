package org.hbrs.se1.ws22.uebung3.persistence;


import org.hbrs.se1.ws22.uebung3.persistence.Container;
import org.hbrs.se1.ws22.uebung3.persistence.ContainerException;
import org.hbrs.se1.ws22.uebung3.persistence.Member;
import org.hbrs.se1.ws22.uebung3.persistence.MemberKonkret;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    private Container container = null;

    @BeforeEach
    void setUp() {
        // Den Container anlegen
        container = Container.getInstance();
    }

    @AfterEach
    void tearDown() {
        container = null;
    }

    @Test
    public void strategieNichtGesetztTest() {
        assertThrows(PersistenceException.class,()-> container.store());
        assertThrows(PersistenceException.class,()-> container.load());
    }

    @Test
    public void strategieMongoDBGesetztTest() {
        container.setPersistenceStrategy(new PersistenceStrategyMongoDB());

        assertThrows(PersistenceException.class,()->container.load());
        assertThrows(PersistenceException.class,()->container.store());
    }

    @Test
    public void strategieStreamGesetztTest() {
        PersistenceStrategyStream s = new PersistenceStrategyStream();
        container.setPersistenceStrategy(s);
        assertEquals(s,container.getPersistenceStrategy());
    }

    @Test
    public void fileNotFoundTest() {

        PersistenceStrategyStream s = new PersistenceStrategyStream();
        container.setPersistenceStrategy(s);
        s.setLocation("test/");  //existierendes directory

        assertThrows(PersistenceException.class,()-> container.load());
        assertThrows(PersistenceException.class,()-> container.store());

        s.setLocation(""); //keine Location angegeben
        assertThrows(PersistenceException.class,()-> container.load());
        assertThrows(PersistenceException.class,()-> container.store());


    }

    @Test
    public void szenarioTest() {
        PersistenceStrategyStream s = new PersistenceStrategyStream();
        container.setPersistenceStrategy(s);
        s.setLocation("test.txt");

        Member member1 = new MemberKonkret(1);
        Member member2 = new MemberKonkret(2);
        Member member3 = new MemberKonkret(3);
        Member member4 = new MemberKonkret(4);

        try {
            container.addMember(member1);
            container.addMember(member2);
            container.addMember(member3);
            container.addMember(member4);
        } catch (ContainerException e) {
            throw new RuntimeException(e);
        }

        assertEquals(4,container.getAnzahl());  //Test ob alle 4 Member in Liste sind

        try {
            container.store();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }

        assertEquals(4,container.getAnzahl());
        Main.view.dump(container.getCurrentList());
        System.out.println();

        container.deleteMember(3);
        assertEquals(3,container.getAnzahl());
        Main.view.dump(container.getCurrentList());
        System.out.println();

        try {
            container.load();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }

        assertEquals(4,container.getAnzahl());
        Main.view.dump(container.getCurrentList());


    }




    @Test
    void addAndDeleteMember() {
        // Test-Objekte anlegen
        Member r1 = new MemberKonkret(12);
        Member r2 = new MemberKonkret(32);
        Member r3 = new MemberKonkret(112);
        Member r4 = new MemberKonkret(1211);
        Member r5 = new MemberKonkret(934);

        // Testfall 1 - Check auf leeren Container
        assertEquals(0, container.getAnzahl(),
                "Testfall 1 - Pruefung auf leeren Store");

        try {
            container.addMember(r1);
            container.addMember(r2);
            container.addMember(r3);
            container.addMember(r4);

        } catch (ContainerException e) {
            e.printStackTrace();
        }


        // Testfall 2 - Pruefen, ob vier Objekte eingefuegt wurden
        assertEquals(4, container.getAnzahl(), "Testfall 2 - Prüfen, ob vier Objekte eingefuegt wurden");


        try {
            container.addMember(r5);
        } catch (ContainerException e) {
            e.printStackTrace();
        }

        // Testfall 3 - Pruefen, ob fuenftes Objekt eingefuegt wurde
        assertEquals(5, container.getAnzahl(), "Testfall 3 - Prüfen, ob fuenftes Objekt eingefuegt wurde");

        String result = container.deleteMember(12);
        // System.out.println( result );

        // Testfall 4 - Pruefen, ob Objekt geloescht wurde
        assertEquals(4, container.getAnzahl(), "Testfall 4 - Prüfen, ob Objekt geloescht wurde");

        result = container.deleteMember(12222);
        System.out.println(result);

        // Testfall 5 - Pruefen, ob ein Objekt faelschlicherweise nicht geloescht wurde
        assertEquals(4, container.getAnzahl(), "Testfall 5 - Pruefen, ob ein Objekt faelschlicherweise nicht geloescht wurde");

        try {
            container.addMember(r2);
        } catch (ContainerException e) {

            e.printStackTrace();

        } finally {

            // Testfall 6 - Pruefen, ob ein Objekt faelschlicherweise nicht doppelt eingefuegt wurde
            assertEquals(4, container.getAnzahl(), "Testfall 6 - Pruefen, ob ein Objekt faelschlicherweise nicht doppelt eingefuegt wurde");
        }
    }

    @Test
    public void dumpAndSizeAndNull() {
        // Test der Dump-Funktion (ohne Kontrolle)
       // container.dump();
        assertEquals(0 , container.getAnzahl()) ;

        // Test der Size-Funktion
        container.getAnzahl();
        assertEquals(0 , container.getAnzahl()) ;

        // Test auf NULL - der "altbekannte" Weg
        try {
            container.addMember(null);
        } catch (ContainerException e) {
            assertEquals( "NULL-Werte dürfen nicht aufgenommen werden!" ,
                    e.getMessage() );
        }
        assertEquals(0 , container.getAnzahl()) ;

        // Test auf NULL - der "moderne" Weg
        assertThrows( ContainerException.class, () -> { container.addMember(null); } );
        assertEquals(0 , container.getAnzahl()) ;
    }
}