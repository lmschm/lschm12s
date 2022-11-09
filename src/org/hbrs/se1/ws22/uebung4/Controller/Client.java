package org.hbrs.se1.ws22.uebung4.Controller;

import org.hbrs.se1.ws22.uebung4.Model.Container;
import org.hbrs.se1.ws22.uebung4.Model.entities.MitarbeiterKonkret;
import org.hbrs.se1.ws22.uebung4.Model.exceptions.PersistenceException;
import org.hbrs.se1.ws22.uebung4.View.MitarbeiterView;
import java.util.List;
import java.util.stream.Collectors;

public class Client {

    public static void dumpAbteilung(Container container,String suchwort, MitarbeiterView view) {
        List<MitarbeiterKonkret> unfilteredList = container.getCurrentList();

        List<MitarbeiterKonkret> filteredList = unfilteredList.stream()
                .filter(mitarbeiter -> mitarbeiter.getAbteilung().contains(suchwort))
                .collect(Collectors.toList());
        view.uebersichtAbteilung(filteredList);
    }

    public static void dumpRolle(Container container,String suchwort, MitarbeiterView view) {
        List<MitarbeiterKonkret> unfilteredList = container.getCurrentList();

        List<MitarbeiterKonkret> filteredList = unfilteredList.stream()
                .filter(mitarbeiter -> mitarbeiter.getRolle().contains(suchwort))
                .collect(Collectors.toList());
        view.uebersichtRolle(filteredList);
    }

    public static void loadMerge(Container container) {
        List<MitarbeiterKonkret> tmp = container.getCurrentList();
        try {
            container.load();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        List<MitarbeiterKonkret> tmp2 = container.getCurrentList();
        for(MitarbeiterKonkret m:tmp) {
            if(!container.contains(m)) {
                tmp2.add(m);
            }
        }
        container.setCurrentList(tmp2);
    }

    public static void loadForce(Container container) {
        try {
            container.load();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
    }

    public static void searchExpertisen(Container container, MitarbeiterView view, String suchwort) {
        List<MitarbeiterKonkret> unfilteredList = container.getCurrentList();

        List<MitarbeiterKonkret> filteredList = unfilteredList.stream()
                .filter(mitarbeiter -> MitarbeiterKonkret.getExpertisenAlsString(mitarbeiter).contains(suchwort))
                .collect(Collectors.toList());
        view.uebersichtExpertisen(filteredList);
    }

}
