package com.wellsfargo.lms.service;

import com.wellsfargo.lms.model.Item;
import com.wellsfargo.lms.model.User;

import java.util.Map;


public interface UserService {
    String saveUser(User user);
    Map<String, Object> login(User userDto);
}
