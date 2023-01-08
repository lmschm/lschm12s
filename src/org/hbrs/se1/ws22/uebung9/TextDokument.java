package org.hbrs.se1.ws22.uebung9;


import java.io.UnsupportedEncodingException;

public class TextDokument extends CoreDocument{

    private int id;
    private Encoding encoding;
    private String text;

    TextDokument(String s,Encoding e) {
        text = s;
        encoding = e;
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
        int anzahl = 0;
        try {
            switch (encoding) {
                case UTF8:
                    byte[] utf8text = new byte[0];
                    utf8text = text.getBytes("UTF-8");
                    anzahl = utf8text.length;
                    break;
                case UTF16:
                    byte[] utf16text = text.getBytes("UTF-16");
                    anzahl = utf16text.length;
                    break;
                case UTF32:
                    byte[] utf32text = text.getBytes("UTF-32");
                    anzahl = utf32text.length;
                    break;
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return anzahl;
    }

    public enum Encoding {
        UTF8,
        UTF16,
        UTF32
    }
}
