package com.wellsfargo.lms.repository;

import com.wellsfargo.lms.model.EmployeeCard;
import com.wellsfargo.lms.model.EmployeeIssueDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeIssueDetailsRepository extends JpaRepository<EmployeeIssueDetails,Long> {
    @Query(value = "SELECT * FROM test.employee_issue_details WHERE employee_id = ?1", nativeQuery = true)
    List<EmployeeIssueDetails> findAllByEmployeeId(String employeeId);
}
