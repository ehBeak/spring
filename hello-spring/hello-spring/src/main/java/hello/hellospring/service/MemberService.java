package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepositroy;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service // 원래는 순수 자바코드였지만 service어노테이션으로 스프링 컨테이너에 등록
@Transactional
public class MemberService {

    // 멤버 저장소 객체
    private final MemberRepositroy memberRepositroy;

    // 생성자 (레파지토리는 하나만 존재하는 것이 맞으니까 매개변수로)
    // 1. @Service에 의해서 스프링 컨테이너에 등록된다.(스프링 빈)
    // 2. 스프링 컨테이너에 등록되었으므로, 스프링 부트 실행하면 MemerService객체 생성
    // 3. MemberService 객체 생성시 생성자가 호출되고 생성자는 @Autowired어노테이션이 붙어있음
    // 4. 생성자를 호출할 시 MemberRepositroy가 필요한 것을 알고 MemberRepository를 주입한다.
    // 5. 이것을 의존관계를 주입한다고 한다. dependency injection
    @Autowired
    public MemberService(MemberRepositroy memberRepositroy) {
        this.memberRepositroy = memberRepositroy;
    }

    /*회원가입*/
    public Long join(Member member){
        validateDuplicateMember(member); // 중복회원 검증
        memberRepositroy.save(member);// 레파지토리에 저장
        return member.getId(); // 회원 id 반환
    }

    // 중복회원 검증 : 같은 이름의 회원은 가입 불가
    public void validateDuplicateMember(Member member){
        memberRepositroy.findByName(member.getName()) // 해당 이름의 멤버객체(Optional) 반환
                .ifPresent(m -> {  // 멤버가 존재하면, 예외처리
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*전체 회원 조회*/
    public List<Member> findMembers() {
        return memberRepositroy.findAll(); // 레파지토리의 모든 멤버 반환
    }

    // ID로 멤버 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepositroy.findById(memberId);// 멤버 아디로 멤버 찾기
    }
}
