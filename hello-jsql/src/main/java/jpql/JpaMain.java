package jpql;

import jpql.domain.Address;
import jpql.domain.Member;
import jpql.domain.MemberType;
import jpql.domain.Team;
import jpql.domain.item.Item;
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

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.changeTeam(team);
            member.setMemberType(MemberType.ADMIN);
            em.persist(member);

            em.flush();
            em.clear();

            // CASE 식 - 기본 CASE 식
            em.createQuery(
                    "select " +
                            "case when m.age <= 10 then '학생요금' " +
                                "when m.age <= 60 then '경로요금' " +
                                "else '기본요금' " +
                            "end " +
                        "from Member m");
            // CASE 식 - 단순 CASE 식
            em.createQuery(
                    "select " +
                            "case m.username " +
                                "when '팀A' then '인센티브110%' " +
                                "when '팀B' then '인센티브120%' " +
                                "else '인센티브105%' " +
                            "end " +
                      "from Member m");
            // COALESCE
            em.createQuery("select coalesce(m.username, '이름 없는 회원') from Member m");
            // NULLIF
            em.createQuery("select nullif(m.username, '관리자') from Member m");


            // 사용자 정의 함수 호출
            em.createQuery("select function('group_concat', m.username) from Member m");
            
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
