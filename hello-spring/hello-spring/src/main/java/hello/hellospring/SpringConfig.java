package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepositroy;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// 컴포넌트 스캔 방식이 아닌, 직접 스프링 빈 등록
@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean // 함수 위에 빈 동록한 뒤 해당 객체 반환
    public MemberService memberService() {
        return new MemberService(memberRepositroy());
    }
    
    @Bean
    public MemberRepositroy memberRepositroy() {

        //return new MemoryMemberRepository(); // memeory
        //return new JdbcMemberRepository(dataSource); //jdbc
        return new JdbcTemplateMemberRepository(dataSource); // jdbcTemplate
    }
}
