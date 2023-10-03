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
public class EmployeeIssueDetails {
    @Id
    @SequenceGenerator(name = "EI_UID", initialValue = 101, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "EI_UID")
    private Long issueId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonManagedReference
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonManagedReference
    private Item item;

    private Date issueDate;
    private Date returnDate;

    @Override
    public String toString() {
        return "EmployeeIssueDetails{" +
                "issueId=" + issueId +
                ", issueDate=" + issueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
