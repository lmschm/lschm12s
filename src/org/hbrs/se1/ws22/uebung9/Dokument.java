package org.hbrs.se1.ws22.uebung9;

import java.io.UnsupportedEncodingException;

public interface Dokument {
    int getID();
    void setID(int id);
    int traversierung() throws UnsupportedEncodingException;
}
