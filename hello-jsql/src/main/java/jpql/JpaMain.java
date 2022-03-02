package jpql;

import jpql.domain.Address;
import jpql.domain.Member;
import jpql.domain.MemberType;
import jpql.domain.Team;
import jpql.domain.item.Item;
import jpql.dto.MemberDTO;

import javax.persistence.*;
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

            // 메인 쿼리와 서브 쿼리를 구분하자.
            // 서브 쿼리 - where, having, select 절에서 사용 가능, from 절에서 서브쿼리 불가능: 조인으로 풀 수 있으면 해결
            // where
            em.createQuery("select m from Member m join Team t on m.username = t.name where exists (select avg(m1.age) from Member m1)");
            // having
            em.createQuery("select m from Member m join Team t on m.username = t.name having (select avg(m1.age) from Member m1)");
            // select
            em.createQuery("select (select avg(m1.age) from Member m1) from Member m join Team t on m.username = t.name");

            // exists-존재하면 참 | ALL-모두 만족하면 참 | ANY,SOME-조건을 하나라도 만족하면 참 | IN-서브쿼리의 결과 중 하나라도 같은 것이 있으면 참

            // JPQL 타입 표현
            // 문자, 숫자, Boolean
            em.createQuery("select TRUE, 'Hello', 10 from Member m");
            // ENUM
            em.createQuery("select m from Member m where m.memberType = jpql.domain.MemberType.ADMIN");
            em.createQuery("select m from Member m where m.memberType = :userType")
                    .setParameter("userType", "jpql.domain.MemberType");
            // 엔티티 타입
            em.createQuery("select i from Item i where type(i) = Book", Item.class); // DTYPE

            // EXISTS, IN, AND, OR, NOT, BETWEEN, LIKE, IS NULL

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
