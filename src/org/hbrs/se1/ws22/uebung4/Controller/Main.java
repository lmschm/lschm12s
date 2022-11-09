package org.hbrs.se1.ws22.uebung4.Controller;

import org.hbrs.se1.ws22.uebung4.Model.Container;
import org.hbrs.se1.ws22.uebung4.Model.PersistenceStrategyStream;
import org.hbrs.se1.ws22.uebung4.Model.entities.MitarbeiterKonkret;
import org.hbrs.se1.ws22.uebung4.View.MitarbeiterView;


public class Main {

    public static void main(String[] args) {
        Container<MitarbeiterKonkret> container = Container.getInstance();
        container.setPersistenceStrategy(new PersistenceStrategyStream());
        MitarbeiterView view = new MitarbeiterView();
        EingabeDialog.eingabe(container, view);

    }
}
