package org.hbrs.se1.ws22.uebung4selber;

import java.io.Serializable;

public class MitarbeiterKonkret implements Mitarbeiter,Serializable {
	
	private Integer id = null;

	private String vorname = "";
	private String nachname = "";
	private String rolle = "";
	private String abteilung = "";
	//expertise
	
	public MitarbeiterKonkret(Integer id, String vorname, String nachname, String rolle, String abteilung){
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.abteilung = abteilung;
		this.rolle = rolle;
		//expertise
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

		return "" + id + "\t" + vorname + "\t" + nachname + "\t" + rolle + "\t" + abteilung ;
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
