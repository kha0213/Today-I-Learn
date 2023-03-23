package hello.selector;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

public class ImportSelectorTest {

    @Test
    @DisplayName("ImportSelector를 사용하지 않는 경우")
    void test() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(StaticConfig.class);
        HelloBean bean = context.getBean(HelloBean.class);
        Assertions.assertThat(bean).isNotNull();
    }

    @Test
    @DisplayName("ImportSelector를 사용하는 경우")
    void test2() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SelectorConfig.class);
        HelloBean bean = context.getBean(HelloBean.class);
        Assertions.assertThat(bean).isNotNull();
    }

    @Configuration
    @Import(HelloConfig.class)
    static class StaticConfig {

    }

    @Configuration
    @Import(HelloImportSelector.class)
    static class SelectorConfig {

    }
}
