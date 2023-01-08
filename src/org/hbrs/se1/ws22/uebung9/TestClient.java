package org.hbrs.se1.ws22.uebung9;

import java.io.UnsupportedEncodingException;

public class TestClient {
    public static void main(String[] args) {
        ComplexDocument doc0 = new ComplexDocument();
        doc0.setID(1);

        TextDokument doc2 = new TextDokument("Die Klausur im Fach SE findet bald statt!", TextDokument.Encoding.UTF8);
        doc2.setID(2);

        ComplexDocument doc3 = new ComplexDocument();
        doc3.setID(3);

        TextDokument doc5 = new TextDokument("Software Engineering I ist eine Vorlesung in den Studiengaengen BIS und BCS",
                TextDokument.Encoding.UTF32);
        doc5.setID(5);

        GraficDokument doc4 = new GraficDokument("localhost:8080");
        doc4.setID(4);

        doc3.addDocument(doc5);
        doc3.addDocument(doc4);

        doc0.addDocument(doc2);
        doc0.addDocument(doc3);

        System.out.println("Die Gesamtgroesse beträgt " + doc0.traversierung() + " Bytes.");

    }
}
