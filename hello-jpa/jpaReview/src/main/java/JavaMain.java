import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JavaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // 양방향 관계인 객체 저장시 주의
            /* 연관관계 주인인 곳이 set을 해줘야 한다.
            *  (읽기 전용에서 set을 해주면 쿼리 날라가지 않음)
            *  -> 그러나 객체지향 스럽지 않고, 2가지 주의점 때문에 list에도 값을 add 해준다
            *  이유
            *  1. 연관관계 주인의 경우에만 set을 해주면 DB말고 1차캐시에만 적용 -> 서로 맞지 않음
            *     -> 연관관계 편의 메소드를 사용하자
            *  2. 양방향 매핑시에 무한 루프를 조심하자. (toString, lombok, JSON)
            *     -> entity를 직접 보내면 안돼! : DTO로 변환해서 반환*/

            tx.commit();

        } catch (Exception e) {
            em.close();
        }

        emf.close();
    }
}
