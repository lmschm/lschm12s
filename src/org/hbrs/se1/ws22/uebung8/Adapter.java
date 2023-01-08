package org.hbrs.se1.ws22.uebung8;

public class Adapter implements HotelSuchenInterface{
    private ReiseAnbieterInterface reiseanbieter = new ReiseAnbieter();


    @Override
    public SuchErgebnis sucheHotel(SuchAuftrag suchauftrag) {
        //Transformierung der Suchanfrage von SuchAuftrag zu QueryObject
        QueryObject anfrage = transformAuftrag(suchauftrag);

        //Ausführung der Methode im ReiseAnbieter
        QueryResult result = reiseanbieter.executeQuery(anfrage);

        //Transformierung QueryResult zu Suchergebnis, damit Client es lesen kann
        SuchErgebnis ergebnis = transformResult(result);

        return ergebnis;
    }

    private QueryObject transformAuftrag(SuchAuftrag suchauftrag) {
        //konkrete Implementierung kann laut Aufgabenstellung vernachlässigt werden
        return null;
    }

    private SuchErgebnis transformResult(QueryResult result) {
        //konkrete Implementierung kann laut Aufgabenstellung vernachlässigt werden
        return null;
    }
}
