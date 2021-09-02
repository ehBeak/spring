package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository // 스프링 컨테이너에 등록
public class MemoryMemberRepository implements MemberRepositroy {

    //저장소
    private static Map<Long, Member> store = new HashMap<>();
    //key값 생성
    private static long sequence = 0L;

    //회원 저장하는 함수
    @Override
    public Member save(Member member) {
        member.setId(++sequence);//키값 증가
        store.put(member.getId(),member);//저장
        return member;//member리턴
    }

    //회원 id로 회원찾기
    @Override
    public Optional<Member> findById(Long id) {
        //Optional.ofNullable로 감싸주면 클라이언트쪽에서 null일때 처리가능
        return Optional.ofNullable(store.get(id));
    }

    //회원 name으로 회원 찾기
    @Override
    public Optional<Member> findByName(String name) {
       //map에서 돌면서 찾기, 그런데 없으면 optional의 null이 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();//하나라도 찾기
    }

    @Override
    public List<Member> findAll() {
        //arrayList를 생성하고 store에 저장된 값들을 넣기 -> 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
