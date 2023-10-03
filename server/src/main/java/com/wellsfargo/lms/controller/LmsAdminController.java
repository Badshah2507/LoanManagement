package com.wellsfargo.lms.controller;

import com.wellsfargo.lms.model.Employee;

import com.wellsfargo.lms.model.Item;
import com.wellsfargo.lms.service.ItemDataService;

import com.wellsfargo.lms.model.LoanCard;
import com.wellsfargo.lms.model.User;
import com.wellsfargo.lms.service.EmployeeDataService;
import com.wellsfargo.lms.service.LoanCardDataService;

import com.wellsfargo.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class LmsAdminController {

    @Autowired
    UserService userService;
    @Autowired
    EmployeeDataService employeeDataService;
    @Autowired
    ItemDataService itemDataService;

    @Autowired
    LoanCardDataService loanCardDataService;

    @GetMapping("/")
    public String welcomeMessage(){
        return "Welcome to LMS";
    }

    @PostMapping("/saveUser")
    public String saveUser(@RequestBody User userDto) {
        return userService.saveUser(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User userDto) {
        Map<String, Object> user = userService.login(userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployee(@RequestBody Employee empDto) {
        String response = employeeDataService.addEmployee(empDto);
        User userDto = new User();
        userDto.setEmployeeId(empDto.getEmployeeId());
        userDto.setPassword("user");
        userDto.setName(empDto.getEmployeeName());
        userDto.setRole("User");
        userService.saveUser(userDto);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> response = employeeDataService.getAllEmployees();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/deleteEmployee")
    public String deleteEmployee(@RequestBody String empId)
    {
        return employeeDataService.deleteEmployee(empId);
    }

    @PostMapping("/addItem")
    public String addItem(@RequestBody Item itemDto) { return itemDataService.addItemData(itemDto); }

    @GetMapping("/getAllItems")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> response = itemDataService.getAllItems();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/deleteItem")
    public String deleteItem(@RequestBody String itemId) { return itemDataService.deleteItemData(itemId); }

    @PostMapping("/addLoanCardDetails")
    public String addLoanCardDetails(@RequestBody LoanCard loanCardDto)
    {
        return loanCardDataService.addLoanCard(loanCardDto);
    }

    @GetMapping("/getAllLoanCards")
    public ResponseEntity<List<LoanCard>> getAllLoanCards()
    {
        List<LoanCard> loanCards = loanCardDataService.getAllLoanCards();

        return new ResponseEntity<>(loanCards, HttpStatus.OK);

    }

    @PostMapping("/deleteLoanCard")
    public String deleteLoanCard(@RequestBody String loanId) { return loanCardDataService.deleteLoanCard(loanId); }
}
