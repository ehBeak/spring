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

            for (int i=0; i<100; i++) {
                Member member = new Member();
                member.setUsername("member" + i);
                member.setAge(i);
                em.persist(member);
            }

            em.flush();
            em.clear();

            /* 페이징 */
            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class) // desc
                    .setFirstResult(1) // 첫번째 튜플부터
                    .setMaxResults(10) // 열번째 튜플까지
                    .getResultList(); // 값 가져오기
            for (Member member1 : result) {
                System.out.println(member1.getUsername());
            }

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


}
