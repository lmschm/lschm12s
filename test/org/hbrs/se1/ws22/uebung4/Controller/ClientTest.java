package org.hbrs.se1.ws22.uebung4.Controller;

import org.hbrs.se1.ws22.uebung4.Model.Container;
import org.hbrs.se1.ws22.uebung4.Model.PersistenceStrategyStream;
import org.hbrs.se1.ws22.uebung4.Model.entities.MitarbeiterKonkret;
import org.hbrs.se1.ws22.uebung4.Model.exceptions.ContainerException;
import org.hbrs.se1.ws22.uebung4.Model.exceptions.PersistenceException;
import org.hbrs.se1.ws22.uebung4.View.MitarbeiterView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    Container container = Container.getInstance();
    MitarbeiterView view = new MitarbeiterView();
    String[][] expertisenM1 = {{"expertise1","1"},{"expertise2","2"}};
    MitarbeiterKonkret m1 = new MitarbeiterKonkret(1,"lisa","schmitz","stud","marketing",expertisenM1);
    String[][] expertisenM2 = {{"expertise1","1"},{"expertise2","2"}};
    MitarbeiterKonkret m2 = new MitarbeiterKonkret(3,"alex","wunderlich","stud","controlling",expertisenM2);
    String[][] expertisenM3 = {{"expertise1","1"},{"expertise2","2"}};
    MitarbeiterKonkret m3 = new MitarbeiterKonkret(2,"michael","mueller","dozent","marketing",expertisenM3);

    @BeforeEach
    void setUp() {
        try {
            container.setPersistenceStrategy(new PersistenceStrategyStream());
            container.addMitarbeiter(m1);
            container.addMitarbeiter(m2);
            container.addMitarbeiter(m3);
        } catch (ContainerException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        container = null;
    }

    @Test
    void dumpAbteilung() {
        assertEquals(3,container.getAnzahl());      //ueberpruefen, ob alle Mitarbeiter geaddet wurden

        String suchwort = "marketing";      //vorhanden

        Client.dumpAbteilung(container,suchwort,view);

        assertEquals(3,container.getAnzahl());      //an der eigentlichen liste soll sich nichts verändert haben

        suchwort = "finanzen";      //nicht vorhanden, gibt nichts aus

        Client.dumpAbteilung(container,suchwort,view);

        assertEquals(3,container.getAnzahl());      //an der eigentlichen liste soll sich nichts verändert haben

    }

    @Test
    void dumpRolle() {
        assertEquals(3,container.getAnzahl());      //ueberpruefen, ob alle Mitarbeiter geaddet wurden

        String suchwort = "stud";      //vorhanden

        Client.dumpRolle(container,suchwort,view);

        assertEquals(3,container.getAnzahl());      //an der eigentlichen liste soll sich nichts verändert haben

        suchwort = "chef";      //nicht vorhanden, gibt nichts aus

        Client.dumpRolle(container,suchwort,view);

        assertEquals(3,container.getAnzahl());      //an der eigentlichen liste soll sich nichts verändert haben

    }

    @Test
    void loadMerge() {
        try {
            container.store();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }

        assertEquals(3,container.getAnzahl());      //Ueberpruefen, ob store etwas an der Anzahl veraendert hat

        Client.loadMerge(container);
        assertEquals(3,container.getAnzahl());      //bei 2 gleichen Mengen sollte nichts veraendert werden

        try {
            container.store();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        container.deleteMitarbeiter(1);
        container.deleteMitarbeiter(2);
        container.deleteMitarbeiter(3);
        String[][] expertisenM4 = {{"expertise1","1"},{"expertise2","2"}};
        MitarbeiterKonkret m4 = new MitarbeiterKonkret(5,"michael","mueller","dozent","marketing",expertisenM4);

        try {
            container.addMitarbeiter(m4);
        } catch (ContainerException e) {
            throw new RuntimeException(e);
        }

        assertEquals(1,container.getAnzahl());      //ueberpruefe, ob der Mitarbeiter geadded wurde

        Client.loadMerge(container);
        assertEquals(4,container.getAnzahl());      //ueberpruefe, ob merge funktioniert hat


    }

    @Test
    void loadForce() {
        try {
            container.store();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }

        String[][] expertisenM4 = {{"expertise1","1"},{"expertise2","2"}};
        MitarbeiterKonkret m4 = new MitarbeiterKonkret(5,"michael","mueller","dozent","marketing",expertisenM4);

        try {
            container.addMitarbeiter(m4);
        } catch (ContainerException e) {
            throw new RuntimeException(e);
        }

        assertEquals(4,container.getAnzahl());      //Ueberpruefen, ob adden des Mitarbeiters funktioniert hat

        Client.loadForce(container);

        assertEquals(3,container.getAnzahl());      //ueberpruefen, ob ueberschrieben wurde


    }

    @Test
    void searchExpertisen() {
        String suchwort = "w";      //nicht vorhandene expertise
        Client.searchExpertisen(container,view,suchwort);       //gibt nichts aus

        assertEquals(3,container.getAnzahl());      //Methode sollte nichts an der eingentlichen anzahl im Container aendern

        suchwort = "expertise1";
        Client.searchExpertisen(container,view,suchwort);       //ausgabe nur der Mitarbeiter mit der Expertise

        assertEquals(3,container.getAnzahl());      //Methode sollte nichts an der eingentlichen anzahl im Container aendern
    }
}