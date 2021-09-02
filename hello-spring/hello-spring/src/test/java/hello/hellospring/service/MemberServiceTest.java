package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // test가 시작할 때마다
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository(); // 레파지토리 생성
        memberService = new MemberService(memberRepository); // 생성한 레파지토리에 대한 MemberService 생성
    }

    @AfterEach // test가 끝날 때마다
    public void afterEach() {
        memberRepository.clearStore(); // 레파지토리 비우기
    }

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();//member객체
        member.setName("hello");

        // when : test -> join 실행
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
        //assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member(); //member1
        member1.setName("spring");

        Member member2 = new Member(); // member2
        member2.setName("spring");

        // when : test
        memberService.join(member1); // join
        //memberService.join(member2); // vaildUpdate에 걸려서 예외가 터져야 함


       /* try {
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/


        //assertThrows(기대하는 예외 , 위의 로직을 실행할 때(람다));
        //로직을 실행할 때 기대하는 예외가 터져야 한다. 그럼 성공
        IllegalStateException e =
                assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        // 예외 메시지가 같은지 확인
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
