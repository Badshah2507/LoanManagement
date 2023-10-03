package com.wellsfargo.lms.service;

import com.wellsfargo.lms.exception.UserNotFound;
import com.wellsfargo.lms.model.User;
import com.wellsfargo.lms.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public String saveUser(User user) {
        try {
            userRepository.save(user);
            return "User Saved :-) !!!!";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "User NOT saved !!!!!!!!!!";
        }
    }

    public User findUserByName(String userName) {
        return userRepository.findByName(userName);
    }

    @Override
    public Map<String, Object> login(User userDto) {
        User user = findUserByName(userDto.getName());
        if (user != null) {
            String pwdInDb = user.getPassword();
            if (pwdInDb.equals(userDto.getPassword())) {
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("name", user.getName());
                responseBody.put("role", user.getRole());
                responseBody.put("empId", user.getEmployeeId());
                return responseBody;
            } else {
                throw new UserNotFound("Password is Incorrect");
            }
        } else {
            throw new UserNotFound("Username doesn't exist");
        }

    }
}
