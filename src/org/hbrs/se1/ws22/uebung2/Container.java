package org.hbrs.se1.ws22.uebung2;

import java.util.LinkedList;

public class Container {

    public static void main(String[] args) throws ContainerException {
        Container c = new Container();
        Member m1 = new ConcreteMember(1);
        Member m2 = new ConcreteMember(2);
        Member m3 = new ConcreteMember(3);

        c.addMember(m1);
        c.addMember(m2);
        c.dump();
        c.deleteMember(1);
        c.dump();
        c.addMember(m2);
        c.dump();
        c.addMember(m3);
        c.addMember(m1);
        c.dump();
        c.deleteMember(3);
        c.dump();
        System.out.println(c.size());
    }

    LinkedList<Member> liste;

   public Container() {
        liste = new LinkedList<>();
    }

    public boolean containsMember(Member member) {
        return liste.contains(member);
    }

    public void addMember(Member member) throws ContainerException{
        if (containsMember(member) ) {
            throw new ContainerException("Das Member-Objekt mit der ID "
                    + member.getID() + " ist bereits vorhanden!");
        }else if(member == null) {
            throw new NullPointerException("Das Member-Objekt darf nicht null sein");
        } else {
            liste.add(member);
        }
    }

    public String deleteMember(Integer id) {
        LinkedList<Member> tmp = new LinkedList<>();
        int existenz = 0;
        while(!liste.isEmpty()) {
            if(liste.getFirst().getID() == id) {
                liste.removeFirst();
                existenz = 1;
            } else {
                tmp.addLast(liste.removeFirst());
            }
        }
        liste = tmp;
        if(existenz == 0) {
            return "Es gibt kein Member-Objekt mit der ID " + id;
        }
        return "";
    }

    public int size() {
        return liste.size();
    }

    public void dump() {
        String ergebnis = "";
        for(int i = 0; i < liste.size(); i++) {
            ergebnis += liste.get(i).toString() + " ";
        }
        System.out.println(ergebnis);
    }

}
