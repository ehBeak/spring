package jpql;

import jpql.domain.Address;
import jpql.domain.Member;
import jpql.domain.MemberType;
import jpql.domain.Team;
import jpql.domain.item.Item;
import jpql.dto.MemberDTO;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.changeTeam(team);
            member.setMemberType(MemberType.ADMIN);
            em.persist(member);

            em.flush();
            em.clear();

            // Named 쿼리: 미리 이름을 정의해서 이름을 부여해두고 사용하는 JPQL, 동적 쿼리 불가
            // 에플리 케이션 로딩 시점에 쿼리를 초기화, 재사용, 검증 가능
            em.createNamedQuery("Member.findByUsername", Member.class)
                            .setParameter("username", "member1")
                            .getResultList();



            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
