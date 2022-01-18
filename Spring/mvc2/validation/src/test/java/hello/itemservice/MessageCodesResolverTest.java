package hello.itemservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import static org.assertj.core.api.Assertions.*;

/**
 * 2021-08-04
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@SpringBootTest
public class MessageCodesResolverTest {
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    @DisplayName("resolveMessageCodes 생성자 인자 2개")
    void messageCodeResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    @DisplayName("resolveMessageCodes 생성자 인자 4개")
    void messageCodeResolverObject2() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        assertThat(messageCodes).containsExactly(
                "required.item.itemName"
                ,"required.itemName"
                ,"required.java.lang.String"
                ,"required"
        );
    }
}
