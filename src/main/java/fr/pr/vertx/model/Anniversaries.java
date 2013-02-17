package fr.pr.vertx.model;

import java.util.*;

public class Anniversaries extends Observable {

    private static volatile Anniversaries instance = null;
    private Set<Person> persons;

    private Anniversaries() {
        super();
        persons = new HashSet<Person>();
    }

    public static Anniversaries getInstance() {
        if (Anniversaries.instance == null) {
            synchronized(Anniversaries.class) {
                if (Anniversaries.instance == null) {
                    Anniversaries.instance = new Anniversaries();
                }
            }
        }
        return Anniversaries.instance;
    }

    public Set<Person> getPersons(){
        return persons;
    }

    public void addPerson(String s) {
        String[] args = s.trim().split("\\|");
        if(3 == args.length) {
            persons.add(new Person(args[0],args[1],args[2]));
            setChanged();
            notifyObservers();
        }
    }
}
