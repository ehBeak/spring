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
             /* 지연로딩 */
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
             System.out.println("findMember: " + member.getTeam().getClass()); // 프록시 타입으로 나옴!
             // Member만 조회했으니까 Member만 조회됨(fetchType.LAZY)

             findMember.getTeam().getName(); // Team에 무언가 터치를 했을 때만 쿼리가 나감



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
