package org.hbrs.se1.ws22.uebung3.persistence;

import java.util.List;

public class Client {

    public static void generiereUndAddeMember(Integer id) throws ContainerException {
        Member member = new MemberKonkret(id);
        try {
            Main.container.addMember(member);
        } catch (ContainerException e) {
            throw new ContainerException("" + member.getID());
        }
    }

    public static void setStrategy(PersistenceStrategy strategy) {
        Main.setStrategy(strategy);
    }

    public static void display() throws ContainerException {
        List<Member> liste = Main.container.getCurrentList();

        Main.view.dump(liste);
    }


}
