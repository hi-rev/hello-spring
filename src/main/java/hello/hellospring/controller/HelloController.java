package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Hello!");
        return "hello";
        // => return 을 통해 templates 에 있는 "hello" 파일을 찾는다.
        // 컨트롤러에서 리턴 값으로 문자를 반환하면 viewResolver 가 화면을 찾아서 처리함
    }
}
