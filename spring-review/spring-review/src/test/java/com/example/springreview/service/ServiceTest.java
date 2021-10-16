package com.example.springreview.service;

import com.example.springreview.domain.Member;
import com.example.springreview.repository.MemberRepository;
import com.example.springreview.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceTest {

    MemoryMemberRepository memberRepository;
    Service service;
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        service = new Service(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clear();
    }

    @Test
    public void join() {
        // given
        Member m = new Member();
        m.setName("spring");

        // when
        Long result = service.join(m);

        //then
        assertThat(result).isEqualTo(m.getId());
    }

    @Test
    private void validDuplicateMember() throws Exception{
        // given
        Member m1 = new Member();
        m1.setName("spring");
        service.join(m1);

        Member m2 = new Member();
        m2.setName("spring");

        // when
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> service.join(m2));
        // !then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}
