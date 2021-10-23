package hellojpa;

import javax.persistence.*;
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
             /* 값타입 컬렉션 저장 */
             Member member = new Member();
             member.setUsername("hello");
             member.setHomeAddress(new Address("homeCity", "zipcode","street"));

             // 값타입 컬렉션 저장(Set<String>)
             member.getFavoriteFoods().add("등촌 샤브샤브 매운버섯탕");
             member.getFavoriteFoods().add("맘스터치 어니언치즈 감튀");
             member.getFavoriteFoods().add("치즈토핑 추가한 임실 고르곤졸라 피자");

             // 값 타입 컬렉션 저장(List<Address>)
             member.getAddressHistory().add(new Address("old1","zipcode","street"));
             member.getAddressHistory().add(new Address("old2","zipcode","street"));

             em.persist(member); // 값타입 컬렉션 또한 값타입이기 때문에 생명주기 같이함(자동으로 persist)

             // 조회
             em.flush();
             em.clear();

             Member findMember = em.find(Member.class, member.getId()); // 지연로딩이라 컬렉션 관련 테이블 안가져옴

             // 컬렉션 관련 테이블 select 하는 시기 : 값을 건들일 때
             Set<String> findFoods = findMember.getFavoriteFoods();
             for(String food : findFoods){
                 System.out.println(food);
             }

             // 수정

             // 1. 값 타입
             //member.getHomeAddress().setCity("A"); : 안돼!!
             member.setHomeAddress(new Address("A","b","c"));

             // 2. 값 타입 컬렉션 Set<String> : 등촌 -> 푸딩
             member.getFavoriteFoods().remove("등촌 샤브샤브 매운버섯탕");
             member.getFavoriteFoods().add("푸딩");

             // 3 값 타입 컬렉션 List<Address> : old1 -> new1 : 통으로 지워짐. 그리고 새로 채움 -> 문제!
             member.getAddressHistory().remove(new Address("old1","zipcode","street")); // equals로 적용
             // : equals hashcode가 그래서 중요!!
             member.getAddressHistory().add(new Address("old1","zipcode","street"));

             tx.commit();
             // DB저장 (flush, commit) => 버퍼링이라는 이점

         } catch (Exception e) {
             tx.rollback(); // 롤백
             e.printStackTrace();
         } finally {
             em.close();
         }

         emf.close();

     }
}
