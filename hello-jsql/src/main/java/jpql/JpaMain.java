package jpql;

import jpql.domain.Address;
import jpql.domain.Member;
import jpql.domain.Team;
import jpql.dto.MemberDTO;

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
            
            // 프로젝션 대상 - 엔티티 타입(영속성 컨텍스트에 관리가 된다. - 프로젝션 엔티티)
            List<Member> findMember = em.createQuery("select m from Member m", Member.class).getResultList();
            List<Team> findTeam = em.createQuery("select m.team from Member m", Team.class).getResultList();

            // 프로젝션 대상 - 임베디드 타입 프로젝션(소속된 엔티티로 부터 시작해야한다.)
            Address findAddress = em.createQuery("select m.address from Member m", Address.class).getSingleResult();

            // 프로젝션 대상 - 스칼라(기본) 타입
            // 1. Query 타입으로 조회
            List findScalar1 = em.createQuery("select distinct m.username, m.age from Member m").getResultList();
            Object o = findScalar1.get(0);
            Object[] result1 = (Object[]) o;
            System.out.println("username = " + result1[0]);
            System.out.println("age = " + result1[1]);

            // 2. Object[] 타입으로 조회
            List<Object[]> findScalar2 = em.createQuery("select distinct m.username, m.age from Member m").getResultList();
            Object[] result2 = findScalar2.get(0);
            System.out.println("username = " + result2[0]);
            System.out.println("age = " + result2[1]);

            // 3. new 명령어로 조회
            List<MemberDTO> result3 = em.createQuery("select distinct new jpql.dto.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();
            MemberDTO memberDTO = result3.get(0);
            System.out.println("username = " + memberDTO.getUsername());
            System.out.println("age = " + memberDTO.getAge());


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
