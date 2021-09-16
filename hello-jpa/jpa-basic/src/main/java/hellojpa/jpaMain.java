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
             /*Member member1 = new Member(150L,"A");
             Member member2 = new Member(160L,"B");

             em.persist(member1);
             em.persist(member2);
             System.out.println("==========");*/

             Member member = em.find(Member.class, 150L);
             member.setName("ZZZZZ");


             /*// 영속 (같은 트랜젝션에서 적용)
             Member findMember1 = em.find(Member.class, 101L); // 쿼리를 DB에서 가져와야 한다.
                                                                    // => 1차 캐시에 없으니까
             Member findMember2 = em.find(Member.class, 101L); // 쿼리를 DB에서 가져오면 안된다.
                                                                    // => 1차 캐시에 있으니까
             System.out.println("result = " + (findMember1 == findMember2));
             // collection으로 보기. => 1차 캐시로 반복 가능한 읽기*/

            /* // 비영속
             Member member = new Member();
             member.setId(101L);
             member.setName("HelloJPA");

             //영속
             System.out.println("=== BEFORE ===");
             em.persist(member); // 쿼리 날리지 않음 => 1차 캐시에서 저장
             System.out.println("=== AFTER ===");

             // 조회
             Member findMember = em.find(Member.class, 101L); // 쿼리 날리지 않음 => 1차 캐시 에서 조회
             System.out.println("findMember.id = " + findMember.getId());
             System.out.println("findMember.name = " + findMember.getName());*/


             tx.commit(); // DB저장 (flush, commit) => 버퍼링이라는 이점
         } catch (Exception e) {
             tx.rollback(); // 롤백
         } finally {
             em.close();
         }

         emf.close();

     }
}
