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

            // 벌크 연산
            // FLUSH 자동 호출
            em.createQuery("update Member m set m.username = m.team.name");


            // 벌크 연산은 영속성 컨텍트를 무시하고 데이터베이스에 직접 쿼리 날림
            // -> 벌크 연산을 먼저 실행
            // -> 벌크 연산 수행 후 영속성 컨텍스트 초기화
            em.createQuery("update Member m set m.username = 'member'");// 영속성 컨텍스트에 반영 안된다. DB만 반영
            em.clear();// 영속성 컨텍스트 초기화

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
