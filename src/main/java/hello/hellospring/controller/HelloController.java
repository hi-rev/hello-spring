package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")  // hello Mapping
    public String hello(Model model) {
        model.addAttribute("data", "Hello!");
        return "hello";
        // => return 을 통해 templates 에 있는 "hello" 파일을 찾는다.
        // 컨트롤러에서 리턴 값으로 문자를 반환하면 viewResolver 가 화면을 찾아서 처리함
    }

    // html 소스를 넘겨줌 (hello-template.html)
    @GetMapping("hello-mvc")  // hello-mvc
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
        // => return 을 통해 templates 에 있는 "hello-template" 파일을 찾는다.
        // 컨트롤러에서 리턴 값으로 변수를 반환하면 viewResolver 가 화면을 찾아서 처리함
    }

    // html 소스 없이 데이터를 그대로 내려줌
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;  // "hello spring"
    }
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
