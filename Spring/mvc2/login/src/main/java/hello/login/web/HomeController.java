package hello.login.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/api/cs/na/qna")
    public String home2(PageRequest pageRequest) {
        log.info("pageRequest={}", pageRequest);
        return "home";
    }
}