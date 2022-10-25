package org.hbrs.se1.ws22.uebung3.persistence;

import java.io.*;
import java.util.List;



public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "objects.ser";
    //private ObjectOutputStream oos;
    //private ObjectInputStream ois;

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    /**
     * Method for opening the connection to a stream (here: Input- and Output-Stream)
     * In case of having problems while opening the streams, leave the code in methods load
     * and save
     */

    public void openConnection() throws PersistenceException {
        /*
        try {
            File file = new File(location);
            //wollen nur Files benutzten, die auch tatsächlich existieren

            oos = new ObjectOutputStream(new FileOutputStream(file));
           ois = new ObjectInputStream(new FileInputStream(file));

        } catch (FileNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoSuchFile,
            "Datei existiert nicht");
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,
            "Es ist ein Fehler aufgetreten. Bitte nocheinmal probieren");
        }
        */

    }

    @Override
    /**
     * Method for closing the connections to a stream
     */
    public void closeConnection() throws PersistenceException {
        /*
        try {
            oos.close();
            ois.close();
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,
            "Es ist ein Fehler aufgetreten. Bitte nocheinmal probieren");
        }

         */
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<E> member) throws PersistenceException  {
        //ObjectOutputStream
        //openConnection();
        //wollte es mit openConnection() und closeConnection() machen, das hat allerdings einfach nicht funktionieren wollen
        //deshalb alles in den Methoden selber
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(location));
            oos.writeObject(member);
            oos.close();
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,
                    "Es ist ein Fehler aufgetreten. Bitte nocheinmal probieren");
        }

        //closeConnection();

    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException {
        // Some Coding hints ;-)

        // ObjectInputStream ois = null;
        // FileInputStream fis = null;
        // List<...> newListe =  null;
        List<E> newListe = null;
        ObjectInputStream ois = null;
        //
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        // fis = new FileInputStream( " a location to a file" );
        // Tipp: Use a directory (ends with "/") to implement a negative test case ;-)
        // ois = new ObjectInputStream(fis);
        try {
            ois = new ObjectInputStream(new FileInputStream(location));
            Object obj = ois.readObject();
            if(obj instanceof List<?>) {
                newListe = (List) obj;
            }
            ois.close();
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,
                    "Es ist ein Fehler aufgetreten");
        } catch (ClassNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable,
                    "Es ist ein Fehler aufgetreten. Bitte erneut versuchen.");
        }

        // openConnection();

        // Reading and extracting the list (try .. catch ommitted here)
        // Object obj = ois.readObject();

        // if (obj instanceof List<?>) {
        //       newListe = (List) obj;
        // return newListe

        // and finally close the streams (guess where this could be...?)
        //closeConnection();

        return newListe;
    }
}
