package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContentTypeTest {

    @Test
    void testEquals() {
        Type expected = new ContentType("test");
        Type actual = new ContentType("test");
        assertEquals(expected, actual);
    }
}