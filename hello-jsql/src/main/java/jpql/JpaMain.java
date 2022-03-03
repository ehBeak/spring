package jpql;

import jpql.domain.Address;
import jpql.domain.Member;
import jpql.domain.MemberType;
import jpql.domain.Team;
import jpql.domain.item.Item;
import jpql.dto.MemberDTO;

import javax.persistence.*;
import java.util.Collection;
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

            /* 페치조인: 연관된 엔티티나 컬렉션을 SQL 한번에 조인하는 기능 */

            // 다대일 - 페치 조인
            // SELECT M.*, T.* FROM Member M INNER JOIN TEAM T ON M.TEAM_ID=T.ID
            em.createQuery("select m from Member m join fetch m.team");

            // 일대다 - 컬렉션 페치 조인
            // SELECT T.*, M.* FROM TEAM T INNER JOIN MEMBER M ON T.ID=M.TEAM_ID WHERE T.NAME='팀A'
            em.createQuery("select t from Team t join fetch t.members"); // team 입장에서 데이터 중복 발생

            // distinct: DB -> 중복제거 실패, 어플리케이션 단계에서 제거
            em.createQuery("select distinct t from Team t join fetch t.members");

            // 페치 조인과 일반 조인의 차이: 페치 조인은 모두 1차 캐시에 올리지만 일반 조인은 1차 캐시에 select 절에 해당하는 객체만 1차캐시에 올림
            // 즉시로딩과 지연로딩 둘 다 n+1문제가 발생한다. 해결 방법이 페치 조인

            /* 페치 조인의 한계
            *  1. 페치 조인 대상에는 별칭을 줄 수 없다.(가급적 사용하지 말자.)
            *  2. 둘 이상의 컬레션은 페치 조인을 할 수 없다.
            *  3. 컬렉션을 페치 조인하면 페이징 API를 사용할 수 없다. -> BatchSize
            * */
            

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
