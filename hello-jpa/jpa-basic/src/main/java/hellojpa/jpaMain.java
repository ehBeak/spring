package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class jpaMain {

     public static void main(String[] args) {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

         EntityManager em = emf.createEntityManager();

         // 트랜잭션 설정 : DB에 접근할 때 트랜잭션 내부에서 해야함.
         EntityTransaction tx = em.getTransaction();
         tx.begin();

         try {
            /*// 영속
             Member member = new Member(200L, "member200");
             em.persist(member); // 트랜잭션에 커밋되기 전까지는 DB에 쿼리 날릴 수가 없다.

             // 미리 DB에 반영을 하고 싶다면..
             em.flush();

             System.out.println("============================");*/

             // 영속
             Member member = em.find(Member.class, 150L);
             member.setName("AAAAA"); // 더티 체킹

             // 준영속 상태
             em.detach(member); // 영속 컨택스트에서 분리시킴 => member와 관련된 모든 것들이 빠짐
             em.clear(); // em과 대응되는 영속성 컨텍스트를 모두 제거
             em.clear(); // 영속성 컨텍스트 종료, em을 종료하니까..



             tx.commit(); // DB저장 (flush, commit) => 버퍼링이라는 이점


         } catch (Exception e) {
             tx.rollback(); // 롤백
         } finally {
             em.close();
         }

         emf.close();

     }
}
