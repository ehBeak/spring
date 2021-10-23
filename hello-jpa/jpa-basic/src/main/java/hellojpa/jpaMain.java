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
             // 값 타입 비교
             // 동일성의 비교 : 값 자체는 같지만 참조값이 다르기 때문에 a != b
             Address a = new Address("city","zipcode", "street");
             Address b = new Address("city","zipcode", "street");

             // 동등성의 비교 : isEqual() : default가 ==으로 되어있기 때문에 오버라이딩
             a.equals(b); // ture

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
