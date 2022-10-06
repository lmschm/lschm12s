package org.hbrs.s1.ws22.uebung1.view;

import org.hbrs.s1.ws22.uebung1.control.GermanTranslator;
import org.hbrs.s1.ws22.uebung1.control.TranslatorFactory;

public class Client {

		/*
		 * Methode zur Ausgabe einer Zahl auf der Console (auch bezeichnet als CLI, Terminal)
		 */
		public void display( int aNumber ){
			// In dieser Methode soll die Methode translateNumber
			// mit dem übergegebenen Wert der Variable aNumber
			// aufgerufen werden.
			//
			// Strenge Implementierung gegen das Interface Translator gewuenscht!

			GermanTranslator erster_translator = (GermanTranslator) TranslatorFactory.erstelleGermanTranslator();
			String ergebnis = erster_translator.translateNumber(aNumber);

			System.out.println("Das Ergebnis der Berechnung: " +
					ergebnis );

		}
}





