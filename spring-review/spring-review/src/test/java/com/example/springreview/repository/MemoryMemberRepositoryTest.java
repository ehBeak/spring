package com.example.springreview.repository;

import com.example.springreview.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    private MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        memberRepository.clear();
    }
    @Test
    public void save(){
        // given
        Member member = new Member();
        member.setName("spring");
        // when
        memberRepository.save(member);
        // !then
        Member result = memberRepository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName(){
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        // when
        Member findMember1 = memberRepository.findByName("spring1").get();

        // then
        assertThat(findMember1).isEqualTo(member1);
    }

    @Test
    public void findById(){
        // given
        Member member = new Member();
        member.setName("spring");
        memberRepository.save(member);
        // when
        Member result = memberRepository.findById(member.getId()).get();
        // then
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findAll(){
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        // when
        List<Member> result = memberRepository.findAll();
        // then
        assertThat(result.size()).isEqualTo(2);
    }

}
