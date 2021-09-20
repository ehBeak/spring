package yoso;

import yoso.domain.Ghs;
import yoso.domain.IngreGhs;
import yoso.domain.Ingredient;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class JavaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            /* insert 하기 */

            // ingredient 3개(H)로.
            Ingredient ingre1 = new Ingredient();
            ingre1.setName("aaa");
            ingre1.setType("H");
            em.persist(ingre1);

            Ingredient ingre2 = new Ingredient();
            ingre2.setName("bbb");
            ingre2.setType("H");
            em.persist(ingre2);


            Ingredient ingre3 = new Ingredient();
            ingre3.setName("ccc");
            ingre3.setType("H");
            em.persist(ingre3);

            System.out.println("========================");

            // ghs
            Ghs ghs1 = new Ghs();
            ghs1.setGhsClass("급성독성");
            ghs1.setCategory("1");
            em.persist(ghs1);

            Ghs ghs2 = new Ghs();
            ghs2.setGhsClass("급성독성");
            ghs2.setCategory("2");
            em.persist(ghs2);

            Ghs ghs3 = new Ghs();
            ghs3.setGhsClass("급성독성");
            ghs3.setCategory("3");
            em.persist(ghs3);

            Ghs ghs4 = new Ghs();
            ghs4.setGhsClass("피부자극성");
            ghs4.setCategory("1A");
            em.persist(ghs4);

            // ingre_ghs
            IngreGhs ingreGhs1 = new IngreGhs();
            ingreGhs1.setIngredient(ingre1);
            ingreGhs1.setGhs(ghs1);
            em.persist(ingreGhs1);

            IngreGhs ingreGhs2 = new IngreGhs();
            ingreGhs2.setIngredient(ingre1);
            ingreGhs2.setGhs(ghs2);
            em.persist(ingreGhs2);

            IngreGhs ingreGhs3 = new IngreGhs();
            ingreGhs3.setIngredient(ingre1);
            ingreGhs3.setGhs(ghs4);
            em.persist(ingreGhs3);


            /* 조회 */
            /* ingre1의 ghs등급을 출력*/

            /*
             * 1. 받은 성분이 h인지 아닌지 확인한다.
             * 2. h이면 join을 해서 h의 몇등급인지 확인한다.*/

            if(ingre1.getType() == "H") {

                IngreGhs findIngreGhs = em.find(IngreGhs.class, ingre1.getId());

                System.out.println("ingre1이 가진 ghs등급 ID: " + findIngreGhs.getGhs());

            } else {
                System.out.println("TYPE: " + "F");
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback(); // 롤백
        } finally {
            em.close();
        }

        emf.close();

    }
}
