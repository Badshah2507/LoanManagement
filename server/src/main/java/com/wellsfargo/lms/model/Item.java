package com.wellsfargo.lms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Item {
    @Id
    private String itemId;
    private String itemDescription;
    private String issueStatus;
    private String itemMake;
    private String itemCategory;
    private Integer itemValuation;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private List<EmployeeIssueDetails> employeeIssueDetails;

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", issueStatus='" + issueStatus + '\'' +
                ", itemMake='" + itemMake + '\'' +
                ", itemCategory='" + itemCategory + '\'' +
                ", itemValuation=" + itemValuation +
                '}';
    }
}
