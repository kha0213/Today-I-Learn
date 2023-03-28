package hello.datasource;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class MyDataSource {
    private String url;
    private String username;
    private String password;
    private int maxConnection;

    private Duration timeout;

    private List<String> options;

    @PostConstruct
    public void init() {
        log.info("MyDataSource initialized with url: {}, username: {}, password: {}, maxConnection: {}, timeout: {}, options: {}",
                url, username, password, maxConnection, timeout, options);
    }
}
