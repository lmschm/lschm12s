package org.hbrs.se1.ws22.uebung4.Model.entities;

import java.io.Serializable;

public class MitarbeiterKonkret implements Mitarbeiter,Serializable {
	
	private Integer id = null;

	private String vorname = "";
	private String nachname = "";
	private String rolle = "";
	private String abteilung = "";
	private String[][] expertisen;
	
	public MitarbeiterKonkret(Integer id, String vorname, String nachname, String rolle, String abteilung, String[][] expertisen){
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.abteilung = abteilung;
		this.rolle = rolle;
		this.expertisen = expertisen;
	}
	@Override
	public String[][] getExpertisen() {

		return expertisen;
	}

	public static String getExpertisenAlsString(Mitarbeiter m) {
		String expertisen = "";
		for (int i = 0; i < m.getExpertisen().length; i++) {
			expertisen += m.getExpertisen()[i][0] + ", Level: ";
			expertisen += m.getExpertisen()[i][1] + "\t";
		}
		return expertisen;
	}

	public void setExpertisen(String[][] expertisen) {
		this.expertisen = expertisen;
	}
	@Override
	public Integer getID() { 
		return this.id;
	}
	
	public void setID ( Integer id ) {
		this.id = id;
	}

	@Override
	public String toString() {
		String s = "" + id + " " + vorname + " " + nachname + " " + rolle + " " + abteilung;
		for(int i = 0; i < expertisen[0].length; i++) {
				s += " " + expertisen[i][0] + " : "+ expertisen[i][1];
		}
		return s;
	}
	@Override
	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	@Override
	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	@Override
	public String getRolle() {
		return rolle;
	}

	public void setRolle(String rolle) {
		this.rolle = rolle;
	}
	@Override
	public String getAbteilung() {
		return abteilung;
	}

	public void setAbteilung(String abteilung) {
		this.abteilung = abteilung;
	}
}
