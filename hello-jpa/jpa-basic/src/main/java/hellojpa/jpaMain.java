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
             /* 영속성 전이: CASCADE */


             Child child1 = new Child();
             Child child2 = new Child();

             Parent parent = new Parent();
             parent.addChild(child1);
             parent.addChild(child2);

             /*em.persist(parent);
             em.persist(child1);
             em.persist(child2);*/
             // 이렇게 해야함 -> 좀 귀찮아.

             /* Parent 가 child를 관리해줬으면 좋겠음 -> 이때 쓰는 것을 cascade라고 함  */

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
