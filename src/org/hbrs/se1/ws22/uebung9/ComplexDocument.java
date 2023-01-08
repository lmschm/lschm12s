package org.hbrs.se1.ws22.uebung9;

import java.util.LinkedList;
import java.util.List;

public class ComplexDocument implements  Dokument{

    private List<Dokument> list = new LinkedList<>();
    private int id;

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int traversierung() {
        int anzahl = 0;
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j) instanceof ComplexDocument) {
                anzahl += list.get(j).traversierung();
            } else if (list.get(j) instanceof GraficDokument) {
                anzahl += list.get(j).traversierung();
            } else if (list.get(j) instanceof TextDokument) {
                anzahl += list.get(j).traversierung();
            } else {
                anzahl += 0;
            }
        }
        return anzahl;
    }

    void removeDocument(Dokument d) {
        if(list.contains(d)) {
            list.remove(d);
        } else {
            System.out.println("Fehler! Das Element existiert nicht in der Liste!");
        }
    }

    void addDocument(Dokument d) {
        if(d.equals(null)) {
            System.out.println("Fehler! Einzufügendes Objekt darf nicht NULL sein!");
        } else {
            list.add(d);
        }
    }
}
