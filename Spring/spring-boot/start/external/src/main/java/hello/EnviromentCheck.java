package hello;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class EnviromentCheck {

    private final Environment env;

    @PostConstruct
    public void init() {
        log.info("env.getProperty(\"url\") : {}", env.getProperty("url"));
        log.info("env.getProperty(\"password\") : {}", env.getProperty("password"));
    }
}
