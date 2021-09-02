package hello.hellospring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");//{data:hello}
        return "hello";//template -> hello.html [viewResolver]
    }

    //static : 요청하면 html파일을 그대로 응답
    //mvc : viewReslover에 model과 html파일명 리턴 -> thymeleaf로 바뀐 html응답
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name")String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    //api방식에서 응답 방식이 문자인 경우
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name")String name){
        return "hello " + name;
    }

    //api방식에서 응답 방식이 객체인 경우
    @GetMapping("hello-api")
    @ResponseBody // default : json반환, http의 body부에 데이터를 넣어서 보낸다.
    public Hello helloApi(@RequestParam("name")String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    //클래스 생성
    static class Hello {
        private String name;

        //자바bean규약
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
