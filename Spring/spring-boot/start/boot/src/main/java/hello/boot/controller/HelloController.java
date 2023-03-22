package hello.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello-spring")
    public String helloSpring() {
        System.out.println("HelloController.helloSpring");
        return "Hello Spring!";
    }

}
