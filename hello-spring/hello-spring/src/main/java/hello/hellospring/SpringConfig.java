package hello.hellospring;

import hello.hellospring.aop.TimeTraceApp;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.swing.*;

// 컴포넌트 스캔 방식이 아닌, 직접 스프링 빈 등록
@Configuration
public class SpringConfig {

    /*private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

   /* //@PersistenceContext
    private EntityManager em;*/

   /* @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
*/
    private final MemberRepositroy memberRepositroy;

    @Autowired
    public SpringConfig(MemberRepositroy memberRepositroy) {
        this.memberRepositroy = memberRepositroy;
    }

    /*@Bean // 함수 위에 빈 동록한 뒤 해당 객체 반환
    public MemberService memberService() {
        return new MemberService(memberRepositroy());
    }*/

    @Bean // 함수 위에 빈 동록한 뒤 해당 객체 반환
    public MemberService memberService() {
        return new MemberService(memberRepositroy);
    }

    /*@Bean
    public TimeTraceApp timeTraceApp() {
        return new TimeTraceApp();
    }*/
   /* @Bean
    public MemberRepositroy memberRepositroy() {

        //return new MemoryMemberRepository(); // memeory
        //return new JdbcMemberRepository(dataSource); //jdbc
        //return new JdbcTemplateMemberRepository(dataSource); // jdbcTemplate
        //return new JpaMemberRepository(em);


    }*/
}
