package org.hbrs.se1.ws22.uebung3.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static Container container = Container.getInstance();
    static MemberView view = new MemberView();

    public static void setStrategy(PersistenceStrategy strategie) {
        container.setPersistenceStrategy(strategie);
    }

}
