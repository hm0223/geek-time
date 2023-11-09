package com.hm.geek.beauty.design.ch34_ch37;

import com.hm.geek.beauty.design.ch34_ch37.refactor2.RandomIdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RandomIdGeneratorTest {
    @Test
    void testGetLastSubstrSplitByDot() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualSubstr = idGenerator.getLastSubstrSplitByDot("field1.field2.field3");
        Assertions.assertEquals("field3", actualSubstr);

        actualSubstr = idGenerator.getLastSubstrSplitByDot("field1");
        Assertions.assertEquals("field1", actualSubstr);

        actualSubstr = idGenerator.getLastSubstrSplitByDot("field1#field2#field3");
        Assertions.assertEquals("field1#field2#field3", actualSubstr);
    }

    @Test
    void testGetLastSubstrSplitByDot_nullOrEmpty() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> idGenerator.getLastSubstrSplitByDot(null), "hostName must not be null");

        String actualSubstr = idGenerator.getLastSubstrSplitByDot("");
        Assertions.assertEquals("", actualSubstr);
    }

    @Test
    void testGenerateRandomAlphameric() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualRandomString = idGenerator.generateRandomAlphameric(6);
        Assertions.assertNotNull(actualRandomString);
        Assertions.assertEquals(6, actualRandomString.length());
        for (char c : actualRandomString.toCharArray()) {
            Assertions.assertTrue(('0' <= c && c <= '9') || ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z'));
        }
    }

    @Test
    void testGenerateRandomAlphameric_lengthEqualsOrLessThanZero() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualRandomString = idGenerator.generateRandomAlphameric(0);
        Assertions.assertEquals("", actualRandomString);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> idGenerator.generateRandomAlphameric(-1), "illegal length " + -1);
    }
}