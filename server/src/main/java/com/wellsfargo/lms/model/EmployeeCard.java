package com.wellsfargo.lms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeCard {

    @Id
    @Setter(AccessLevel.NONE)
    @SequenceGenerator(name = "EC_UID", initialValue = 101, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "EC_UID")
    private Long EmployeeCardId;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    @JsonManagedReference
    private LoanCard loanCard;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonManagedReference
    private Employee employee;

    private Date cardIssueDate;

    @Override
    public String toString() {
        return "EmployeeCard{" +
                "employeeCardId=" + EmployeeCardId +
                ", cardIssueDate=" + cardIssueDate +
                '}';
    }
}
