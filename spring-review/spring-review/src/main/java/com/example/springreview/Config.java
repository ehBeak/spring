package com.example.springreview;

import com.example.springreview.repository.MemoryMemberRepository;
import com.example.springreview.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return memberRepository();
    }
}
