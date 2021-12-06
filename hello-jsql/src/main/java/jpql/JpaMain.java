package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member3 = new Member();
            member3.setUsername("member1");
            member3.setAge(10);
            em.persist(member3);

            Member member = new Member();
            member.setUsername("member2");
            member.setAge(10);
            em.persist(member);

            // TypeQuery : 반환 타입이 명확할 때 사용
            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
            TypedQuery<String> query1 = em.createQuery("select m.username from Member m", String.class);

            // Query : 반환 타입이 명확하지 않을 때 사용
            Query query2 = em.createQuery("select m.username, m.age from Member m");

            // 결과 조회 API

            // 1. 값이 여러개일때
            List<Member> resultList = query.getResultList();

            for(Member member1 : resultList) {
                System.out.println(member1);
            }

            // 2. 값이 무조건 하나일때(진짜 결과가 하나일 때만 사용해야함)
            /*Member result2 = query.getSingleResult();
            System.out.println(result2);*/

            // 2-1 예외)값이 하나 이상일 때 : 리스트 반환, javax.persistence.NonUniqueResultException
            // 2-2 예외)값이 아예 없을 때 : 빈리스트 반환, javax.persistence.NoResultException

            // 파라미터 바인딩
            Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();

            System.out.println("result = " + result.getUsername());

            /*TypedQuery<Member> query3 = em.createQuery("select m from Member m where m.username = :username", Member.class);
            query3.setParameter("username", "member1");
            System.out.println(query3.getSingleResult().getUsername());*/
            em.flush();

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


}
