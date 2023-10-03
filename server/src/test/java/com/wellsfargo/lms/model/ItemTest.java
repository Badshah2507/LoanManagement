package com.wellsfargo.lms.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ItemTest {
    /**
     * Method under test: {@link Item#toString()}
     */
    @Test
    void testToString() {
        Item item = new Item();
        item.setEmployeeIssueDetails(new ArrayList<>());
        item.setIssueStatus("Issue Status");
        item.setItemCategory("Item Category");
        item.setItemDescription("Item Description");
        item.setItemId("42");
        item.setItemMake("Item Make");
        item.setItemValuation(42);
        assertEquals(
                "Item{itemId='42', itemDescription='Item Description', issueStatus='Issue Status', itemMake='Item Make',"
                        + " itemCategory='Item Category', itemValuation=42}",
                item.toString());
    }
}

