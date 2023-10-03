package com.wellsfargo.lms.service;

import com.wellsfargo.lms.model.User;
import com.wellsfargo.lms.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;
    @MockBean
    UserRepository userRepository;
    User userObj;

    @BeforeEach
    void setUp() {
        userObj = new User();
    }

    @AfterEach
    void tearDown() {
        userObj = null;
    }

    @Test
    void saveUser() {
        userObj = new User(1L,"Om", "pwd", "Admin", 1L);

        String response = userService.saveUser(userObj);
        assertEquals("User Saved :-) !!!!", response);
    }

    @Test
    void login() {
        userObj = new User(1L,"Om", "pwd", "Admin", 1L);
        when(userRepository.findByName(any(String.class))).thenReturn(userObj);

        Map<String, Object> response = userService.login(userObj);
        assertEquals("Om", response.get("name"));
        assertEquals("Admin", response.get("role"));
        assertEquals(1L, response.get("empId"));
    }
}