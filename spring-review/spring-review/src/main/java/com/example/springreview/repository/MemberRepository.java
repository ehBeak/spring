package com.example.springreview.repository;

import com.example.springreview.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByName(String name);
    Optional<Member> findById(Long id);
    List<Member> findAll();
}
