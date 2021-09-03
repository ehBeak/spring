package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepositroy;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 스프링 테스트 할 때
@Transactional // DB에 커밋(저장)을 한 뒤 '롤백'한다. -> 지운다기 보다는 데이터를 반영하지 않음
public class MemberServiceIntegrationTest {

    // 생성자 주입을 쓰기보다는 테스트니까 걍 필드주입으로 하자..
    @Autowired MemberService memberService;
    @Autowired MemberRepositroy memberRepositroy;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();//member객체
        member.setName("hello");

        // when : test -> join 실행
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberRepositroy.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
        //assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member(); //member1
        member1.setName("hello");

        Member member2 = new Member(); // member2
        member2.setName("hello");

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
