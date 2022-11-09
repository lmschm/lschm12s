package org.hbrs.se1.ws22.uebung4.Model;

import org.hbrs.se1.ws22.uebung4.Model.entities.MitarbeiterKonkret;
import org.hbrs.se1.ws22.uebung4.Model.exceptions.ContainerException;
import org.hbrs.se1.ws22.uebung4.Model.exceptions.PersistenceException;
import org.hbrs.se1.ws22.uebung4.View.MitarbeiterView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    private Container container = null;
    MitarbeiterView view = new MitarbeiterView();

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
    void addAndDeleteMember() {
        // Test-Objekte anlegen
        String[][] expertisenM1 = {{"expertise1","1"},{"expertise2","2"}};
        MitarbeiterKonkret m1 = new MitarbeiterKonkret(1,"lisa","schmitz","stud","marketing",expertisenM1);
        String[][] expertisenM2 = {{"expertise1","1"},{"expertise2","2"}};
        MitarbeiterKonkret m2 = new MitarbeiterKonkret(3,"alex","wunderlich","stud","controlling",expertisenM2);
        String[][] expertisenM3 = {{"expertise1","1"},{"expertise2","2"}};
        MitarbeiterKonkret m3 = new MitarbeiterKonkret(2,"michael","mueller","dozent","marketing",expertisenM3);
        MitarbeiterKonkret m4 = new MitarbeiterKonkret(4,"michael","mueller","dozent","marketing",expertisenM3);


        // Testfall 1 - Check auf leeren Container
        assertEquals(0, container.getAnzahl(),
                "Testfall 1 - Pruefung auf leeren Store");

        try {
            container.addMitarbeiter(m1);
            container.addMitarbeiter(m2);
            container.addMitarbeiter(m3);

        } catch (ContainerException e) {
            e.printStackTrace();
        }


        // Testfall 2 - Pruefen, ob drei Objekte eingefuegt wurden
        assertEquals(3, container.getAnzahl(), "Testfall 2 - Prüfen, ob drei Objekte eingefuegt wurden");


        try {
            container.addMitarbeiter(m4);
        } catch (ContainerException e) {
            e.printStackTrace();
        }

        // Testfall 3 - Pruefen, ob viertes Objekt eingefuegt wurde
        assertEquals(4, container.getAnzahl(), "Testfall 3 - Prüfen, ob viertes Objekt eingefuegt wurde");

        container.deleteMitarbeiter(1);

        // Testfall 4 - Pruefen, ob Objekt geloescht wurde
        assertEquals(3, container.getAnzahl(), "Testfall 4 - Prüfen, ob Objekt geloescht wurde");

        try {
            container.addMitarbeiter(m2);
        } catch (ContainerException e) {

            e.printStackTrace();

        } finally {

            // Testfall 6 - Pruefen, ob ein Objekt faelschlicherweise nicht doppelt eingefuegt wurde
            assertEquals(3, container.getAnzahl(), "Testfall 6 - Pruefen, ob ein Objekt faelschlicherweise nicht doppelt eingefuegt wurde");
        }
    }

}