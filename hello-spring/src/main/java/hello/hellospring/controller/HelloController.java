package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    //MVC 방식
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model)
    {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name)
    {
        return "hello "+ name;
    }

    //API 방식
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name)
    {
        Hello hello = new Hello();
        hello.setName(name);
        //객체가 ResposeBody로 들어오면 조건에 맞게 HttpMessageConverter가 동작
        //단순 문자면 StringConverter, 객체면 JsonConverter(Jackson2 Lib)
        //
        return hello;
    }
    static class Hello{
        private String name;

        //프로퍼티 접근방식 Getter and Setter
        public String getName()
        {
            return name;
        }
        public  void setName(String name)
        {
            this.name = name;
        }
    }
}
