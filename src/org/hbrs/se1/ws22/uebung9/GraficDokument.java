package org.hbrs.se1.ws22.uebung9;

public class GraficDokument extends CoreDocument{

    private int id;
    private String url;

    GraficDokument(String url) {
        this.url = url;
    }


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
        return 1200;
    }
}
