package hello.hellospring.Controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 1. @Component : 컴포넌트 생성과 등록(스프링빈)
// 2. @Autowired : 컴포넌트끼리 연결 (의존관계 설정)

@Controller
public class MemberController {
     private final MemberService memberService;
//   private final MemberService memberService = new MemberService();

    // 생성자 위에 어노테이션 하면, 멤버 서비스를 스프링 컨테이너에 가져다 연결시킴
    // 스프링부트에서는 모든 것을 스프링 컨테이너에서 가져다 씀
    // MemberController가 생성이 될 때
    // 생성자에 의해서 MemberService의 객체를 넣어준다.(이것도 service어노테이션)
    // 이것이 Dependency injection : 의존관계를 주입하는 것
    @Autowired // 스프링 컨테이너에 등록하기
     public MemberController(MemberService memberService) {
        this.memberService = memberService;
     }

     @GetMapping("/members/new") // 조회
     public String createForm() {
        return "members/createMemberForm";
     }

     @PostMapping("/members/new") // 등록 (from태그)
      public String create(MemberForm form) {
         Member member = new Member();
         member.setName(form.getName());

         memberService.join(member);

         return "redirect:/";
      }

      @GetMapping("/members")
      public  String list(Model model) {
          List<Member> members = memberService.findMembers();
          model.addAttribute("members",members);
          return "members/memberList";
      }

}
