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
             // embeded type
             Member member = new Member();
             member.setUsername("hello");
             member.setHomeAddress(new Address("city", "street", "zipcode"));
             member.setPeriod(new Period());

             em.persist(member);

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
