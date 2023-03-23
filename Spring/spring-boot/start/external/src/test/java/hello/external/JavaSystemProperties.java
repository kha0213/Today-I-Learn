package hello.external;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaSystemProperties {
    public static void main(String[] args) {
        System.getProperties()
                .forEach((key, value) -> log.info("key: {}, value: {}", key, value));

        String url = System.getProperty("url");
        String password = System.getProperty("password");

        log.info("url : {}, password : {}", url, password);
    }
}
