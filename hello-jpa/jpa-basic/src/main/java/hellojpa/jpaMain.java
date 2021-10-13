package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class jpaMain {

     public static void main(String[] args) {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

         EntityManager em = emf.createEntityManager();

         // 트랜잭션 설정 : DB에 접근할 때 트랜잭션 내부에서 해야함.
         EntityTransaction tx = em.getTransaction();
         tx.begin();

         try {
             // movie 등록 : item테이블에 insert 쿼리, movie테이블에 insert쿼리가 각각 나간다.
             Movie movie = new Movie();
             movie.setDirector("aaaa");
             movie.setActor("bbbb");
             movie.setName("바람과 함께 사라지다");
             movie.setPrice(10000);

             em.persist(movie);

             em.flush();
             em.close();

             //검색시 movie를 부모클래스와 join을 한 뒤 가져옴
             Movie findMovie = em.find(Movie.class,movie.getId());
             System.out.println("findMovie = " + findMovie);


             tx.commit(); // DB저장 (flush, commit) => 버퍼링이라는 이점


         } catch (Exception e) {
             tx.rollback(); // 롤백
         } finally {
             em.close();
         }

         emf.close();

     }
}
