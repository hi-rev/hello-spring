package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    // 객체를 생성하지 말고 스프링 컨테이너에 생성을 해둔다.
    // Autowired를 통해 객체의 생성과 제어권을 Spring한테 넘김
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
