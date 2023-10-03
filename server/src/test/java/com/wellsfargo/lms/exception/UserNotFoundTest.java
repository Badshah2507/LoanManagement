package com.wellsfargo.lms.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.wellsfargo.lms.model.User;
import com.wellsfargo.lms.repository.UserRepository;
import com.wellsfargo.lms.service.UserService;

@SpringBootTest
public class UserNotFoundTest {

	@Autowired
	UserService userService;
	
	@MockBean
	UserRepository userRepository;
	
	User userObj;
	
	@BeforeEach
	void setUp()
	{
		userObj = new User();
	}
	
	@AfterEach
	void tearDown()
	{
		userObj = null;
	}
	
	@Test
	void testUserNotExists()
	{
		userObj = new User(1L,"abcd", "pwd", "Admin", 1L);
		
		when(userRepository.findByName("abcd")).thenReturn(null);

		Exception exception = assertThrows(UserNotFound.class,() -> {
			userService.login(userObj);
		});
		
		assertEquals("Username doesn't exist",exception.getMessage());
	}
	
	@Test
	void testUserPwdIncorrect()
	{
		userObj = new User(105L,"Ipsum", "xyz1234", "Admin", null);
		
		when(userRepository.findByName("Ipsum")).thenReturn(userObj);

		Exception exception = assertThrows(UserNotFound.class,() -> {
				userService.login(new User(105L,"Ipsum","abc123","Admin",null));
			});
		
		assertEquals("Password is Incorrect",exception.getMessage());
	}
}
