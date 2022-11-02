package org.hbrs.se1.ws22.uebung4selber;

public class Client {

    public static void main(String[] args) throws ContainerException, PersistenceException {
        Container container = Container.getInstance();
        container.addMember(new MitarbeiterKonkret(6,"alex","wunderlich","student","hbrs"));
        if(args.length != 0) {
            switch (args[0]) {
                case "enter":
                    String abteilung = "";
                    Integer id = Integer.parseInt(args[1]);
                    String vorname = args[2];

                    String nachname = args[3];
                    String rolle = args[4];
                    if(args.length == 5) {
                        abteilung = "nicht vorhanden";
                    } else {
                        abteilung = args[5];
                    }
                    container.addMember(new MitarbeiterKonkret(id, vorname, nachname, rolle, abteilung));
                    break;
                case "store":
                    container.setPersistenceStrategy(new PersistenceStrategyStream());
                    container.store();
                    break;
                case "load":
                    container.setPersistenceStrategy(new PersistenceStrategyStream());
                    if(args[1].equals("force")) {
                        container.load();
                    } else if(args[1].equals("merge")) {
                        System.out.println("noch nicht implementiert");
                    }
                    break;
                case "search":
                    break;
                case "dump":
                    break;
                case "exit":
                    break;
                case "help":
                    System.out.println("Liste aller Befehle: ");
                    System.out.print("enter id vorname nachname rolle abteilung[optional]\n" +
                            "store\nload merge\nload force\nsearch [expertise]\ndump\nexit\nhelp\n");
                    break;
            }
        } else {
            System.out.println("Es wurde kein Befehl eingegeben!");
        }
        container.uebersicht();
    }
}
