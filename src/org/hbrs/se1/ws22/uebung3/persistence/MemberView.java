package org.hbrs.se1.ws22.uebung3.persistence;

import java.util.List;

public class MemberView {

    public void dump(List<Member> liste) {
        for (Member member : liste) {
            System.out.println(member.toString());
        }
    }
}
