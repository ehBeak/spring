package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
//TDD : 태스트 주도 개발(test작성 후 구현) -> 일단 이건 TDD가 아님
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repositroy = new MemoryMemberRepository();

    @AfterEach // Test한 번 할때마다 밑의 함수 실행
    public void afterEach() {
        repositroy.clearStore();// Map을 비운다.
    }

    @Test //@Test는 해당 함수를 실행시킴
    public void save() {
        //given
        Member member = new Member();//멤버 생성
        member.setName("spring");//멤버 이름 설정

        //when : 함수 실행해보기
        repositroy.save(member);

        // then
        // optional은 get()을 통해서 꺼낼 수 있다.
        Member result = repositroy.findById(member.getId()).get();

        //System.out.println("result = "+(result == member));
        //Assertions.assertEquals(member,result);//expected, actual
        //Assertions.assertThat(member).isEqualTo(result);
        // 반환된 member와 result가 같은지 확인
        assertThat(member).isEqualTo(result);//member가 result와 같은지

        //성공 : 초록불, 실패 : 빨간불
    }

    @Test
    public void findByName() {
        // given
        Member member1 = new Member(); // member1
        member1.setName("spring1");
        repositroy.save(member1);

        Member member2 = new Member(); // member2
        member1.setName("spring2");
        repositroy.save(member2);

        // when : findByName()실행 -> test
        Member result = repositroy.findByName("spring1").get();

        // then : result(test)가 member(real)와 같은지 확인
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        // given
        Member member1 = new Member(); // member1
        member1.setName("spring1");
        repositroy.save(member1);

        Member member2 = new Member(); // member2
        member1.setName("spring2");
        repositroy.save(member2);

        // when : findAll()실행 -> test
        List<Member> result = repositroy.findAll();

        // result(test)의 배열크기와 2(real)가 같은지 확인
        assertThat(result.size()).isEqualTo(2);
    }
}
