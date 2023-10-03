package com.wellsfargo.lms.service;

import com.wellsfargo.lms.model.Item;
import com.wellsfargo.lms.model.LoanCard;
import com.wellsfargo.lms.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ItemDataServiceImplTest {

    @Autowired
    private ItemDataServiceImpl itemDataServiceImpl;

    @Autowired
    ItemDataService itemDataService;
    @MockBean
    ItemRepository itemRepository;


    @Test
    void addItemData() {
        Item itemObj = Item.builder()
                .itemId("I0001")
                .itemCategory("Furniture")
                .itemDescription("Tea Table")
                .itemMake("Wooden")
                .itemValuation(5000)
                .build();

        String response = itemDataService.addItemData(itemObj);
        assertEquals("Item Saved Succesfully", response);
    }

    /**
     * Method under test: {@link ItemDataServiceImpl#addItemData(Item)}
     */
    @Test
    void testAddItemData() {
        Item item = new Item();
        item.setEmployeeIssueDetails(new ArrayList<>());
        item.setIssueStatus("Issue Status");
        item.setItemCategory("Item Category");
        item.setItemDescription("Item Description");
        item.setItemId("42");
        item.setItemMake("Item Make");
        item.setItemValuation(42);
        when(itemRepository.save(Mockito.<Item>any())).thenReturn(item);

        Item itemDto = new Item();
        itemDto.setEmployeeIssueDetails(new ArrayList<>());
        itemDto.setIssueStatus("Issue Status");
        itemDto.setItemCategory("Item Category");
        itemDto.setItemDescription("Item Description");
        itemDto.setItemId("42");
        itemDto.setItemMake("Item Make");
        itemDto.setItemValuation(42);
        assertEquals("Item Saved Succesfully", itemDataServiceImpl.addItemData(itemDto));
        verify(itemRepository).save(Mockito.<Item>any());
    }

    @Test
    void getAllItems() {
        Item itemObj1 = Item.builder()
                .itemId("I0001")
                .itemCategory("Furniture")
                .itemDescription("Tea Table")
                .itemMake("Wooden")
                .itemValuation(5000)
                .build();
        Item itemObj2 = Item.builder()
                .itemId("I0002")
                .itemCategory("Stationery")
                .itemDescription("Pen")
                .itemMake("Plastic")
                .itemValuation(500)
                .build();
        List<Item> res = new ArrayList<>();
        res.add(itemObj1);
        res.add(itemObj2);

        when(itemRepository.findAll()).thenReturn(res);

        List<Item> response = itemDataService.getAllItems();
        assertEquals("I0001", response.get(0).getItemId());
        assertEquals("I0002", response.get(1).getItemId());
        assertEquals("Furniture", response.get(0).getItemCategory());
        assertEquals("Stationery", response.get(1).getItemCategory());
        assertEquals("Tea Table", response.get(0).getItemDescription());
        assertEquals("Pen", response.get(1).getItemDescription());
        assertEquals("Wooden", response.get(0).getItemMake());
        assertEquals("Plastic", response.get(1).getItemMake());
        assertEquals(5000, response.get(0).getItemValuation());
        assertEquals(500, response.get(1).getItemValuation());
    }

    /**
     * Method under test: {@link ItemDataServiceImpl#getAllItems()}
     */
    @Test
    void testGetAllItems() {
        ArrayList<Item> itemList = new ArrayList<>();
        when(itemRepository.findAll()).thenReturn(itemList);
        List<Item> actualAllItems = itemDataServiceImpl.getAllItems();
        assertSame(itemList, actualAllItems);
        assertTrue(actualAllItems.isEmpty());
        verify(itemRepository).findAll();
    }

    /**
     * Method under test: {@link ItemDataServiceImpl#deleteItemData(String)}
     */
    @Test
    void testDeleteItemData() {
        doNothing().when(itemRepository).deleteById(Mockito.<String>any());
        assertEquals("Item was deleted!", itemDataServiceImpl.deleteItemData("42"));
        verify(itemRepository).deleteById(Mockito.<String>any());
    }

}