package com.example.springreview.repository;

import com.example.springreview.domain.Member;
import org.springframework.lang.Nullable;

import java.util.*;

import static java.util.Optional.*;

public class MemoryMemberRepository implements MemberRepository {

    /* !메모리 저장소 */
    public static Map<Long, Member> store = new HashMap<>(); // static -> 모든 객체가 공유
    /* sequence */
    public static Long sequence = 0L;

    /* 회원 저장 */
    @Override
    public Member save(Member member) { // !이미 member객체가 넘어옴
         /* !중복 확인 */

        member.setId(sequence++);
        store.put(member.getId(), member); // 저장소에 넣기
        return member;
    }

    /* 회원 이름으로 찾기*/
    @Override
    public Optional<Member> findByName(String name) {
        /* !List의 값들을 찾아서 필터를 거침(이름이 같은 애를 찾기) */
        return store.values().stream().
                filter(member -> member.getName().equals(name)).findAny();
    }

    /* 회원 아이디로 찾기 */
    @Override
    public Optional<Member> findById(Long id) {
        return ofNullable(store.get(id));
    }

    /* 모든 회원 찾기 */
    @Override
    public List<Member> findAll() {
        /* map의 값을 arrayList로 변환 */
        return new ArrayList<>(store.values());
    }

    public void clear() {
        store.clear();
    }
}
