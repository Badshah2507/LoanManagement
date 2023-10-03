package com.wellsfargo.lms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoanCard {

    @Id
    private String loanId;
    private String loanType;
    private Integer loanDurationYrs;

    @OneToMany(mappedBy = "loanCard")
    @JsonIgnore
    private List<EmployeeCard> employeeCards;

    @Override
    public String toString() {
        return "LoanCard{" +
                "loanId='" + loanId + '\'' +
                ", loanType='" + loanType + '\'' +
                ", loanDurationYrs=" + loanDurationYrs +
                '}';
    }
}
