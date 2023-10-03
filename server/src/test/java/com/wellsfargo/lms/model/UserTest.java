package com.wellsfargo.lms.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserTest {
    /**
     * Method under test: {@link User#setPassword(String)}
     */
    @Test
    void testSetPassword() {
        User user = new User();
        user.setPassword("iloveyou");
        assertEquals("aWxvdmV5b3U=", user.getPassword());
    }
}

