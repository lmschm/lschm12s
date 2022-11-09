package org.hbrs.se1.ws22.uebung4.Model;

import org.hbrs.se1.ws22.uebung4.Model.entities.Mitarbeiter;
import org.hbrs.se1.ws22.uebung4.Model.exceptions.ContainerException;
import org.hbrs.se1.ws22.uebung4.Model.exceptions.PersistenceException;

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
	private List<Mitarbeiter> liste = new ArrayList<>(); // key, value

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
		return this.liste;
	}
	public void setCurrentList(List<Mitarbeiter> liste) {
		this.liste = liste;
	}
	/*
	 * Methode zum Hinzufuegen einer Member.
	 * @throws ContainerException
	 */ 
	public void addMitarbeiter(E r) throws ContainerException {
		if ( contains(r) == true ) {
			ContainerException ex = new ContainerException( r.getID().toString() );
			throw ex;
		}
		if (r == null) {
			ContainerException ex = new ContainerException(r.getID().toString());
			throw ex;
		}

		liste.add( r );

	} 
	
	/*
	 * Methode zur Ueberpruefung, ob ein Member-Objekt in der Liste enthalten ist
	 * 
	 */
	public boolean contains(E r) {
		Integer ID = r.getID();
		for ( Mitarbeiter rec : liste) {
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
	public String deleteMitarbeiter(Integer id) {
		Mitarbeiter rec = getMitarbeiter(id);
		if (rec == null) {
			return "Member nicht enthalten - ERROR";
		} else {
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

	/*
	 * Interne Methode zur Ermittlung einer Member
	 * 
	 */
	private Mitarbeiter getMitarbeiter(Integer id) {
		for ( Mitarbeiter rec : liste) {
			if (id == (rec).getID().intValue() ){
				return (Mitarbeiter) rec;
			}
		}
		return null;
	}

}
