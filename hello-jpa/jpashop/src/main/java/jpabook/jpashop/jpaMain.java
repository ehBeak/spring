package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class jpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 저장: 양뱡향 관계일 때 주의
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.ChangeTeam(team); // 연관관계 편의 메소드
            em.persist(member);

//            em.flush();
//            em.clear();

            // 조회 : 단방향
            /*Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());*/

            // 조회 : 양방향
            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for (Member m :
                    members) {
                System.out.println("m = " + m.getUsername());
            }

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members1 = findTeam.getMembers();

            System.out.println("========");
            System.out.println("members = " + findTeam); // 무한루프: toString, lombok, JSON생성 라이브러리
            System.out.println("========");

            

            tx.commit();
        } catch (Exception e) {
            tx.rollback(); // 롤백
        } finally {
            em.close();
        }

        emf.close();

    }

}
