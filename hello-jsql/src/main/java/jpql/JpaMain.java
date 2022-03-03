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

            // 경로 표현식: 묵시적 조인을 쓰지말고 명시적 조인(join 키워드를 직접 사용)을 쓰자.

            // 상태필드: 단순히 값 저장(경로 탐색의 끝, 더이상 탐색이 안된다.)
            em.createQuery("select m.username from Member m");

            // 연관 필드 - 단일 값 연관 필드: 대상이 엔티티(묵시적 내부 조인 발생, 탐색 가능)
            em.createQuery("select m.team from Member m", Team.class);

            // 연관 필드 - 컬렉션 값 연관 필드: 대상이 컬렉션(묵시적 내부 조인 발생, 탐색 불가)
            em.createQuery("select t.members from Team t", Collection.class);

            //==> 탐색 불가 해결: 명시적 조인 사용
            em.createQuery("select m.username from Team t join t.members m", Collection.class);

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
