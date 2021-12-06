package hellojpa;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class jpaMain {

     public static void main(String[] args) {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

         EntityManager em = emf.createEntityManager();

         // 트랜잭션 설정 : DB에 접근할 때 트랜잭션 내부에서 해야함.
         EntityTransaction tx = em.getTransaction();
         tx.begin();

         try {
             // jpql -> 동적 쿼리 힘들다
             String qlString = "select m from Member m like %hello%";
             List<Member> result = em.createQuery(qlString, Member.class).
                     getResultList();
             // criteria -> 동적쿼리 : 쿼리를 코드로 짠다.
             // 사전 준비
             CriteriaBuilder cb = em.getCriteriaBuilder();
             CriteriaQuery<Member> qurey = cb.createQuery(Member.class);

             // 루트 클래스 : 조회를 시작할 클래스
             Root<Member> m = qurey.from(Member.class);

             // 동적으로 쿼리를 만든다.
             CriteriaQuery<Member> cq = qurey.select(m).where(cb.equal(m.get("username"), "kim"));
             List<Member> resultList = em.createQuery(cq).getResultList();


             // querydsl : criteria대안

             tx.commit();
             // DB저장 (flush, commit) => 버퍼링이라는 이점 : 라이브러리

             // 네이티브 sql
             String sql = "SELECT ID, AGE, TEAM_ID, NAME FROM MEMBER WHERE NAME = ‘kim’";
             List<Member> resultList1 = em.createNativeQuery(sql, Member.class).getResultList();


             //또는 jdbcTemplate, jdbc직접 사용
             
         } catch (Exception e) {
             tx.rollback(); // 롤백
             e.printStackTrace();
         } finally {
             em.close();
         }

         emf.close();

     }
}
