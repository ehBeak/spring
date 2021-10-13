package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
             // SuperClass 상속받은 MemberClass -> create 쿼리는 1개

             Member member = new Member();
             member.setCreateBy("aaa");
             member.setCreatedDate(LocalDateTime.now());
             em.persist(member);

             em.flush();
             em.close();




             tx.commit(); // DB저장 (flush, commit) => 버퍼링이라는 이점


         } catch (Exception e) {
             tx.rollback(); // 롤백
         } finally {
             em.close();
         }

         emf.close();

     }
}
