package com.example.javabasic.stringutil;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringUtilsTest {
    @Test
    void stringUtilTest() {
        // null 이거나 "" 인 경우 true
        assertTrue(StringUtils.isEmpty(""));
        // 인자가 null일 경우 ""로 바꿔준다.
        assertEquals("", StringUtils.defaultString(null));
        // 인자의 string을 뒤집어 준다.
        assertEquals("edcba", StringUtils.reverse("abcde"));
        // 인자 String만큼 반복해준다.
        assertEquals("*****", StringUtils.repeat("*", 5));
        // 앞에 해당 char만큼 붙여준다. (숫자나 날짜 계산에 편하다)
        assertEquals("0010", StringUtils.leftPad("10", 4, '0'));
        // 알파벳만 있는지 검사해준다.
        assertTrue(StringUtils.isAlpha("abcdddee"));
    }
}
