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

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);


            em.flush();
            em.clear();

            /* 엔티티 프로젝션 */
            // 엔티티 프로젝션을 하면 그 결과값이 영속성 컨텍스트에서 다 관리가 된다.
            // 때문에 그 결과값을 set으로 update하면 DB에 값이 다 반영되는 것임임
            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .getResultList();

            Member findMember = result.get(0);
            findMember.setAge(20); // query 날라감

            // join query 날라감(묵시적 조인)
            List<Team> findTeam = em.createQuery("select m from Member m", Team.class)
                    .getResultList();

            // sql 쿼리랑 최대한 같게 jpql을 사용하기(명시적 조인)
            List<Team> findTeam2 = em.createQuery("select t from Member m join m.team t", Team.class)
                    .getResultList();


            /* 임베디드 타입 프로젝션
            *  -> "select address from Address o" 이렇게는 할 수가 없음 */
            List<Address> findAddress = em.createQuery("select o.address from Order o", Address.class)
                    .getResultList();

            /* 스칼라 타입 프로젝션 */
            // 필드값 그냥 가져오는 것임
            em.createQuery("select distinct m.username, m.age from Member m").getResultList();

            // 쿼리의 결과가 타입이 두 개인데 어떻게 해결하지?
            // 방법1. List로 받아서 타입 캐스팅을 한다.
            List resultList = em.createQuery("select distinct m.username, m.age from Member m").getResultList();

            Object o = resultList.get(0);
            Object[] result2 = (Object[]) o;

            System.out.println("result = " + result2[0]); // username
            System.out.println("result = " + result2[1]); // age

            // 방법2. 이 과정에서 TypeQuery사용하기
            List<Object[]> resultList1 = em.createQuery("select distinct m.username, m.age from Member m").getResultList();
            Object[] result3 = resultList1.get(0);

            System.out.println("result = " + result3[0]); // username
            System.out.println("result = " + result3[1]); // age

            // 방법3. new 명령어로 조회
            List<MemberDTO> resultList2 = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();

            MemberDTO memberDTO = resultList2.get(0);
            System.out.println("result = " + memberDTO.getUsername()); // username
            System.out.println("result = " + memberDTO.getAge()); // age



            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


}
