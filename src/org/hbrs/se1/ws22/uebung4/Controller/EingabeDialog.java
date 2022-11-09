package org.hbrs.se1.ws22.uebung4.Controller;

import org.hbrs.se1.ws22.uebung4.Model.Container;
import org.hbrs.se1.ws22.uebung4.Model.entities.MitarbeiterKonkret;
import org.hbrs.se1.ws22.uebung4.Model.exceptions.ContainerException;
import org.hbrs.se1.ws22.uebung4.Model.exceptions.PersistenceException;
import org.hbrs.se1.ws22.uebung4.View.MitarbeiterView;

import java.util.List;
import java.util.Scanner;

public class EingabeDialog {
    public static void eingabe(Container container, MitarbeiterView view) {
        Scanner sc = new Scanner(System.in);
        System.out.println("VTool V.1.1");
        System.out.println("Willkommen bei VTool, ihr Tool zur einfachen Verwaltung ihrer Mitarbeiter.");
        System.out.println("Für weitere Informationen nutzen Sie bitte den Befehl help.");

        while(true) {
            System.out.println("Bitte Befehl eingeben:");
            System.out.print("> ");
            String befehl = sc.next();

            if(befehl.equals("help")) {
                System.out.println("Mögliche Befehle:\n" +
                        "help - gibt eine Liste mit allen möglichen Befehlen aus\n" +
                        "exit - schließt die Anwendung\n" +
                        "enter - fügt einen Mitarbeiter hinzu\n" +
                        "store - speichert eine Liste von eingegebenen Mitarbeitern persistent ab\n" +
                        "load force - lädt eine persistent abgespeicherte Liste und überschreibt die Aktuelle\n" +
                        "load merge - lädt eine persistent abgespeicherte Liste und vereinigt diese mit den Aktuellen\n" +
                        "search - Suche nach Expertisen und Ausgabe der passenden Mitarbeiter");

            } else if(befehl.equals("exit")) {
                System.out.println("Das Tool wird jetzt beendet.");
                sc.close();
                return;

                //US 1
            } else if(befehl.equals("enter")) {
                System.out.println("Sollte eine der geforderten Angaben nicht bekannt sein, verwenden Sie bitte /.");
                System.out.print("ID: ");
                Integer id = sc.nextInt();

                System.out.print("Vorname: ");
                String vorname = sc.next();
                if(!vorname.matches("\\D*")) {
                    System.out.println("Ungueltige Eingabe. Bitte verwenden Sie keine Ziffern!");
                    continue;
                }

                System.out.print("Nachname: ");
                String nachname = sc.next();
                if(!nachname.matches("\\D*")) {
                    System.out.println("Ungueltige Eingabe. Bitte verwenden Sie keine Ziffern!");
                    continue;
                }

                System.out.print("Rolle: ");
                String rolle = sc.next();
                if(!rolle.matches("\\D*")) {
                    System.out.println("Ungueltige Eingabe. Bitte verwenden Sie keine Ziffern!");
                    continue;
                }

                System.out.print("Abteilung: ");
                String abteilung = sc.next();


                System.out.print("Bitte Anzahl der Expertisen eingeben: ");
                Integer anzahlExpertisen = sc.nextInt();
                String[][] expertisen = new String[anzahlExpertisen][anzahlExpertisen+1];
                for(int i = 0; i < anzahlExpertisen; i++) {
                    System.out.print("Expertisen: ");
                    expertisen[i][0] = sc.next();
                    System.out.print("Level der Expertise (1-3): ");
                    expertisen[i][1] = sc.next();
                }

                try {
                    container.addMitarbeiter(new MitarbeiterKonkret(id,vorname,nachname,rolle,abteilung,expertisen));
                } catch (ContainerException e) {
                    throw new RuntimeException(e);
                }

                //US 2 und US 1 (von Übung)
            } else if(befehl.equals("dump")) {
                System.out.print("Falls Sie eine Gesamtuebersicht haben möchten, geben Sie bitte uebersicht ein." +
                        "Ansonsten geben Sie bitte / ein: ");
                String kriterium = sc.next();
                if(kriterium.equals("uebersicht")) {
                    System.out.println("Es wird eine Uebersicht sortiert nach der Id erstellt. Einen Moment Geduld bitte.");
                    List<MitarbeiterKonkret> liste = container.getCurrentList();

                    liste.sort((mitarbeiter1, mitarbeiter2) -> mitarbeiter1.getID().compareTo(mitarbeiter2.getID()));

                    view.uebersicht(liste);
                } else {
                    System.out.print("Bitte geben Sie an, wonach Sie ihre Ausgabe richten wollen (abteilung/rolle): ");
                    String ausgabe = sc.next();
                    if (ausgabe.equals("abteilung")) {
                        System.out.print("Bitte geben Sie die Abteilung an: ");
                        String suchwort = sc.next();
                        Client.dumpAbteilung(container,suchwort,view);

                    } else if(ausgabe.equals("rolle")) {
                        System.out.print("Bitte geben Sie die Rolle an: ");
                        String suchwort = sc.next();
                        Client.dumpRolle(container,suchwort,view);
                    }
                }

                //US 3
            } else if(befehl.equals("store")) {
                try {
                    container.store();
                } catch (PersistenceException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Liste erfolgreich abgespeichert.");

                //US 3
            } else if(befehl.equals("load")) {
                System.out.println("Bitte wählen Sie merge oder force: ");
                String parameter = sc.next();
                if(parameter.equals("force")) {
                    Client.loadForce(container);
                    System.out.println("Liste erfolgreich geladen.");
                } else if(parameter.equals("merge")) {
                    Client.loadMerge(container);
                    System.out.println("Liste erfolgreich geladen.");
                }

                //US 4
            } else if(befehl.equals("search")) {
                System.out.print("Bitte Expertise nach der gesucht werden soll eingeben: ");
                String suchwort = sc.next();
                Client.searchExpertisen(container,view,suchwort);

            } else {
                System.out.println("Dieser Befehl existiert momentan nicht. Bitte schauen Sie mit help nach, " +
                        "welche Befehle von diesem System unterstuetzt werden.");
            }
        }
    }
}
