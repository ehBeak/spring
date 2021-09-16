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

         // 트랜잭션 설정
         EntityTransaction tx = em.getTransaction();
         tx.begin();

         try {

             /*1.
             //삭제
             Member findMember = em.find(Member.class, 1L);
             em.remove(findMember);*/

             /*2.
             //조회
             Member findmember = em.find(Member.class, 1L);
             System.out.println("findMember.id = "+findmember.getId());
             System.out.println("findMember.name = "+findmember.getName());*/

             /*3.
             //삽입
             // 생성, 설정
             Member member = new Member();
             member.setId(1L);
             member.setName("helloA");

             em.persist(member);// 삽입*/

             /*4.
             // 수정
             Member findmember = em.find(Member.class, 1L);
             findmember.setName("HelloJPA"); // persist필요 없음!*/

             /* 나이가 18살인 모든 회원을 검색하고 있다면? -> JPQL: 객체 지향 쿼리 언어 */
             // 객체를 대상으로한 쿼리
             // m = Member Entity
             List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();

             for(Member member : result) {
                 System.out.println("member.name = " + member.getName());
             }


             tx.commit(); // DB저장
         } catch (Exception e) {
             tx.rollback(); // 롤백
         } finally {
             em.close();
         }

         emf.close();

     }
}
