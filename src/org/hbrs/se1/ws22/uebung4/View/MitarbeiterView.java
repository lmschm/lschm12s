package org.hbrs.se1.ws22.uebung4.View;

import org.hbrs.se1.ws22.uebung4.Model.entities.Mitarbeiter;
import org.hbrs.se1.ws22.uebung4.Model.entities.MitarbeiterKonkret;

import java.util.List;

public class MitarbeiterView {

    public void uebersicht(List<MitarbeiterKonkret> liste) {
        System.out.format("%-15s%-22s%-22s%-22s%-22s","Id","Vorname","Nachname","Rolle","Abteilung");
        System.out.println();
        for(Mitarbeiter m : liste) {
            System.out.printf("%-15d%-22s%-22s%-22s%-22s", m.getID(),m.getVorname(),m.getNachname(),
                    m.getRolle(),m.getAbteilung());
            System.out.println();
        }
    }

    public void uebersichtAbteilung(List<MitarbeiterKonkret> liste) {
        System.out.format("%-15s%-22s%-22s","Vorname","Nachname","Abteilung");
        System.out.println();
        for(Mitarbeiter m : liste) {
            System.out.printf("%-15s%-22s%-22s",m.getVorname(),m.getNachname(),m.getAbteilung());
            System.out.println();
        }
    }

    public void uebersichtRolle(List<MitarbeiterKonkret> liste) {
        System.out.format("%-15s%-22s%-22s%-22s","Id","Vorname","Nachname","Rolle");
        System.out.println();
        for(Mitarbeiter m : liste) {
            System.out.printf("%-15d%-22s%-22s%-22s", m.getID(),m.getVorname(),m.getNachname(),m.getRolle());
            System.out.println();
        }
    }

    public void uebersichtExpertisen(List<MitarbeiterKonkret> liste) {
        System.out.format("%-15s%-22s%-22s%-22s","Id","Vorname","Nachname","Expertisen");
        System.out.println();
        for(Mitarbeiter m : liste) {
            System.out.printf("%-15d%-22s%-22s%-22s", m.getID(),m.getVorname(),m.getNachname(),
                    MitarbeiterKonkret.getExpertisenAlsString(m));
            System.out.println();
        }
    }
}
