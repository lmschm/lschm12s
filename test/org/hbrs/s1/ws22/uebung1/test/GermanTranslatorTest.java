package org.hbrs.s1.ws22.uebung1.test;

import org.hbrs.s1.ws22.uebung1.control.GermanTranslator;

import static org.junit.jupiter.api.Assertions.*;

class GermanTranslatorTest {

    @org.junit.jupiter.api.Test
    void translateNumber() {
        GermanTranslator translator = new GermanTranslator();
        assertEquals("zwei",translator.translateNumber(2));
        assertEquals("Übersetzung der Zahl -1 nicht möglich (1.0)",translator.translateNumber(-1));
        assertEquals("Übersetzung der Zahl 12 nicht möglich (1.0)",translator.translateNumber(12));
        assertEquals("Übersetzung der Zahl 0 nicht möglich (1.0)",translator.translateNumber(0));
    }
}