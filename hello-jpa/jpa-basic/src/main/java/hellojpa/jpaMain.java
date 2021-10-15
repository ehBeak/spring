package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class jpaMain {

     public static void main(String[] args) {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

         EntityManager em = emf.createEntityManager();

         // 트랜잭션 설정 : DB에 접근할 때 트랜잭션 내부에서 해야함.
         EntityTransaction tx = em.getTransaction();
         tx.begin();

         try {
             /* 즉시로딩 */
             Team team = new Team();
             team.setName("teamA");
             em.persist(team);

             Member member = new Member();
             member.setUsername("m");
             member.setTeam(team);

             em.persist(member);

             em.flush();
             em.clear();

             Member findMember = em.find(Member.class, member.getId());
             System.out.println("findMember: " + member.getTeam().getClass()); // 프록시랑 관련 없음, 안나옴
             // 한번에 같이 조인해서 실제 엔티티를 가져옴 (fetchType.EAGER)

             findMember.getTeam().getName(); // : 이전에 초기화 끝

             tx.commit();
             // DB저장 (flush, commit) => 버퍼링이라는 이점

         } catch (Exception e) {
             tx.rollback(); // 롤백
             e.printStackTrace();
         } finally {
             em.close();
         }

         emf.close();

     }
}
