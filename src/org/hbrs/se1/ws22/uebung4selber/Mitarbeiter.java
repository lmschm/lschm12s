package org.hbrs.se1.ws22.uebung4selber;

public interface Mitarbeiter {
	
	// ID ist in einem abgeleiteten Objekt über eine Konstruktor-Methode zu belegen 
	// --> Primaerschluessel zur Unterscheidung aller Member-Objekte
	Integer getID();
	String getVorname();
	String getNachname();
	String getRolle();
	String getAbteilung();
	
	public String toString();

}
 