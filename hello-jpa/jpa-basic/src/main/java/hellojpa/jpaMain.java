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
             // em.find() VS em.getReference()
             Member member = new Member();
             member.setUsername("hello");

             em.persist(member);

             em.flush(); // DB 쿼리 날아감
             em.clear(); // 영속성 컨텍스트 완전히 깨끗하게 -> 처음 어플리케이션 실행한 것 처럼

             // find() -> 한번에 조인을 해서 조회용 쿼리를 날림
             Member findMember = em.find(Member.class, member.getId());
             System.out.println("userid: " + findMember.getId());
             System.out.println("username: " + findMember.getUsername());

             // getReference() -> getReference만으로 쿼리가 나가는 것 아님
             Member findMember2 = em.getReference(Member.class, member.getId());
             System.out.println("findMember2 = " + findMember2.getClass());
             // -> 출력 내용 hibernateProxy ~~ : hibernate가 만든 가짜 클래스 = 프록시 클래스스
             System.out.println("userid: " + findMember2.getId()); // 쿼리 안나감
             System.out.println("username: " + findMember2.getUsername()); // username은 DB에 있으니까 쿼리 나감
             // -> JPA가 실제 어떤 값이 필요할 때 DB에 쿼리가 나감(현재는 getUsername())

             /* find()와 getReference의 반환 클래스 타입은 다르다. ==이 아니라 instance of*/


             /* 준영속 상태에서 프로시 초기화 하면 문제 발생 */
             Member m1 = em.getReference(Member.class, member.getId());
             em.detach(m1); // 준영속
             m1.getUsername(); // 여기서 em이 초기화 해야하는데 못하니까 예외 발생


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
