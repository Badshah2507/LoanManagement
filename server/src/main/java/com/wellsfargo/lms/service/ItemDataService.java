package com.wellsfargo.lms.service;

import com.wellsfargo.lms.model.Item;

import java.util.List;

public interface ItemDataService {
    String addItemData(Item itemDto);
    List<Item> getAllItems();
    String deleteItemData(String itemId);
}
