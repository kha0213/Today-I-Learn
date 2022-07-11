package com.example.javabasic.stringutil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringUtilsTest2 {
    @Test
    void springStringUtils() {
        // apache의 isEmpty와 비슷하다 null 이다 "" 이면 false 이다.
        assertFalse(StringUtils.hasLength(""));
        // apache의 isBlank와 비슷하다 null, "", "  " 이면 false이다.
        assertFalse(StringUtils.hasText(""));

        // collection의 string을 join한다.
        List<String> list = List.of("apple", "banana", "lemon");
        assertEquals("apple|banana|lemon", StringUtils.collectionToDelimitedString(list, "|"));
        // 사실상 String의 join을 더 자주 쓰긴 한다.
        assertEquals("apple|banana|lemon", String.join("|", list));

        // 파일 이름을 출력한다 (확장자 포함)
        assertEquals("test.txt",StringUtils.getFilename("D:/app/test.txt"));
        // 파일 확장자만 출력한다.
        assertEquals("txt",StringUtils.getFilenameExtension("D:/app/test.txt"));
    }
}
