package hello.controller;

import lombok.extern.slf4j.Slf4j;
import memory.Memory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        log.info("hello");
        return "Hello, world!";
    }
}
