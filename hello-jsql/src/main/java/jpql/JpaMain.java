package jpql;

import jpql.domain.Member;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();

            // TypeQuery: 반환 타입이 명확
            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);

            // Query: 반환 타입이 명확하지 않을 때
            Query query3 = em.createQuery("select m.username, m.age from Member m");

            // 결과 반환 - 컬렉션 조회 (결과가 없으면 빈 리스트 반환)
            List<Member> MemberList = query1.getResultList();

            // 결과 반환 - 딱 하나 조회 (결과가 없으면 NotResultException, 결과가 둘 이상이면 NoUniqueResultException 예외발생)
            Member member1 = query1.getSingleResult();

            // 파라미터 바인딩 - 이름 기준
            Member binding_name = em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", member1)
                    .getSingleResult();

            // 파라미터 바인딩 - 위치 기준 (권장X)
            Member binding_pos = em.createQuery("select m from Member m where m.username = ?1", Member.class)
                    .setParameter(1, member1)
                    .getSingleResult();


            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
