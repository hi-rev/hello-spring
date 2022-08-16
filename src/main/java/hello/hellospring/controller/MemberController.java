package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    // 객체를 생성하지 말고 스프링 컨테이너에 생성을 해둔다.
    // Autowired를 통해 객체의 생성과 제어권을 Spring한테 넘김
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 가입
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        // 리다이렉트는 웹 브라우저가 어떤 URL로 웹 서버를 요청했을 때 다른 URL로 넘겨주는 것을 말한다.
        // 예를 들어 페이지에 로그인이 되어 있지 않다면, 로그인이 선행되어야 하기 때문에 로그인 페이지로 이동시키는 것
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        // model에 데이터를 담을 때 addAttribute()를 사용
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
