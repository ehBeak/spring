package hellojpa;

import javax.persistence.Entity;

@Entity
public class Team {
    private Long id;
    private Member member;
}
