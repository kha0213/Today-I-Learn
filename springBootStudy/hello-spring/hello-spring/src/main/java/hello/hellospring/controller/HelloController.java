package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 2021-01-26
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","hello");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name",name);
        return "hello-mvc";
    }

    @GetMapping("hello-api")
    @ResponseBody
    public String helloApi(String name, Model model) {
        return "hello-api";
    }

    @GetMapping("hello-api2")
    @ResponseBody
    public Hello helloApi2(@RequestParam String name, Model model) {
        Hello hello = new Hello(name);
        return hello;
    }
    static class Hello {
        private String name;

        public Hello(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
