package org.hbrs.se1.ws22.uebung4selber;


import java.util.ArrayList;
import java.util.List;


public class Container<E extends Mitarbeiter> {


	//Teil des Singelton-Design-Patterns
	private static Container container;

	//zum setzen der Persistenz-Strategie
	private PersistenceStrategy strategie;


	/* 
	 * Interne ArrayList zur Abspeicherung der Objekte
	 */
	private List<E> liste = new ArrayList<E>(); // key, value

	/*
	 * Leerer Konstruktor, private für Singleton Pattern
	 */
	private Container(){
	}

	/*
	 * Methode, die dafür da ist, erstens eine Instanz von GenericContainer zu bekommen
	 * und zweitens zu überprüfen, ob schone eine Instanz existiert (soll nur eine möglich sein)
	 */
	public static Container getInstance() {
		if(container == null) {
			container = new Container();
		}
		return container;
	}

	/*
	 * Methode, die die Strategie im Container setzt
	 *
	 */
	public void setPersistenceStrategy(PersistenceStrategy strategie) {
		this.strategie = strategie;
	}

	/*
	 * Methode, um die Persistence-Strategie zu bekommen, die gesetzt wurde
	 *
	 */
	public PersistenceStrategy getPersistenceStrategy() {
		return strategie;
	}

	/*
	 * Methode, um Member-Objekte auf einem Datenspeicher zu speichern
	 * @throws PersistenceException
	 */
	public void store() throws PersistenceException {
		if(strategie instanceof PersistenceStrategyMongoDB<?>) {
			throw new PersistenceException(PersistenceException.ExceptionType.StrategyNotSupported,
					"Diese Strategie wird nicht unterstützt. Bitte eine neue wählen.");
		} else if(strategie == null) {
			throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,
					"Bitte Strategie setzen.");
		} else if(strategie instanceof PersistenceStrategyStream<?>){
			strategie.save(liste);
		}
	}

	/*
	 * Methode, um Member-Objekte zu laden
	 * @throws PersistenceException
	 */
	public void load() throws PersistenceException {
		if(strategie instanceof PersistenceStrategyMongoDB<?>) {
			throw new PersistenceException(PersistenceException.ExceptionType.StrategyNotSupported,
					"Diese Strategie wird nicht unterstützt. Bitte eine neue wählen.");
		} else if(strategie == null) {
			throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,
					"Bitte Strategie setzen.");
		} else if(strategie instanceof PersistenceStrategyStream<?>){
			this.liste = strategie.load();
		}
	}

	/*
	 * Methode, um die Liste zurückzugeben
	 *
	 */
	public List<Mitarbeiter> getCurrentList() {
		return (List<Mitarbeiter>) this.liste;
	}

	/*
	 * Methode zum Hinzufuegen einer Member.
	 * @throws ContainerException
	 */ 
	public void addMember (E r) throws ContainerException {
		if ( contains(r) == true ) {
			ContainerException ex = new ContainerException( r.getID().toString() );
			throw ex;
		}
		if(liste.size() == 0) {
			liste.add(r);
		} else {
			int index = 0;
			for(int i = 0;i < liste.size();i++) {
				if(r.getID() <=  liste.get(i).getID()) {
					index = i;
					break;
				} else if (r.getID() > liste.get(i).getID()){
					index +=1;
					continue;
				}
			}
			liste.add(index,r);
		}

		
	} 
	
	/*
	 * Methode zur Ueberpruefung, ob ein Member-Objekt in der Liste enthalten ist
	 * 
	 */
	private boolean contains(E r) {
		Integer ID = r.getID();
		for ( E rec : liste) {
			// wichtig: Check auf die Values innerhalb der Integer-Objekte!
			if ( (rec).getID().intValue() == ID.intValue() ) {
				return true;
			}
		}
		return false;
		
		// liste.contains(r), falls equals-Methode in Member ueberschrieben.
	}
	/*
	 * Methode zum Loeschen einer Member
	 * 
	 */
	public String deleteMember(Integer id) {
		Mitarbeiter rec = getMember(id);
		if (rec == null) return "Member nicht enthalten - ERROR"; else {
			liste.remove(rec);
			return "Member mit der ID " + id + " konnte geloescht werden";
		}
	}
	
	/*
	 * Methode zur Bestimmung der Anzahl der von Member-Objekten
	 * Aufruf der Methode size() aus List
	 * 
	 */
	public int getAnzahl(){
		return liste.size();
	}

	public void uebersicht() {
		System.out.format("%s %20s %20s %20s %30s","Id","Vorname","Nachname","Rolle","Abteilung");
		System.out.println();
		for(Mitarbeiter m : liste) {
			System.out.format("%d %20s %20s %20s %30s", m.getID(),m.getVorname(),m.getNachname(),
					m.getRolle(),m.getAbteilung());
			System.out.println();
		}
	}

	/*
	 * Methode zur Ausgabe aller IDs der Member-Objekte, wegen Übung 3 auskommentiert
	 * Ausgelagert in MemberView
	 *
	public void dump(){
		System.out.println("Ausgabe aller Memberen: ");
		// Loesung mit For each:
		for ( E p : liste ) {
			System.out.println( p.toString()  );
		}
		
		// Loesung mit Iterator:
		// Iterator<Member> i = liste.iterator();
		//  while (  i.hasNext() ) {
		//	   Member p = i.next();
		//	   System.out.println("ID: " + p.getID() );
		//  }
	}*/

	/*
	 * Interne Methode zur Ermittlung einer Member
	 * 
	 */
	private Mitarbeiter getMember(Integer id) {
		for ( E rec : liste) {
			if (id == (rec).getID().intValue() ){
				return (Mitarbeiter) rec;
			}
		}
		return null;
	}

}
