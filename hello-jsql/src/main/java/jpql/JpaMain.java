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

            // 엔티티 직접 사용 - 기본 키값: 둘 다 실행되는 SQL이 같다.
            // SELECT COUNT(M.ID) AS CNT FROM MEMBER M;
            em.createQuery("select count(m.id) from Member m");
            em.createQuery("select  count (m) from Member m");

            // SELECT M.* FROM MEMBER M WHERE M.ID=?
            em.createQuery("select m from Member m where m = :member")
                            .setParameter("member", member)
                            .getResultList();
            em.createQuery("select m from Member m where m.id = :memberId")
                    .setParameter("memberId", member.getId())
                    .getResultList();

            // 엔티티 직접 사용 - 외래 키 값
            // SELECT M.* FROM MEMBER M WHERE M.TEAM_ID=?
            em.createQuery("select m from Member m where m.team = :team")
                            .setParameter("team", team)
                            .getResultList();
            em.createQuery("select m from Member m where m.team.id = :teamID")
                    .setParameter("teamID", team.getId())
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
