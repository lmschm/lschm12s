package org.hbrs.s1.ws22.uebung1.control;

public class TranslatorFactory {

    public static Translator erstelleGermanTranslator() {
        GermanTranslator translator = new GermanTranslator();
        translator.setDate("06.10.2022");
        return translator;
    }

}
