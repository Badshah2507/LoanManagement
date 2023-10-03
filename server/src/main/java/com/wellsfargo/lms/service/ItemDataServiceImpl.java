package com.wellsfargo.lms.service;

import com.wellsfargo.lms.exception.DataNotFound;
import com.wellsfargo.lms.model.Item;
import com.wellsfargo.lms.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ItemDataServiceImpl implements ItemDataService{
    @Autowired
    ItemRepository itemRepository;
    @Override
    public String addItemData(Item itemDto) {
        try {
            itemRepository.save(itemDto);
            return "Item Saved Succesfully";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Item NOT saved :-( !!!";
        }
    }

    @Override
    public List<Item> getAllItems() {
        try {
            return itemRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public String deleteItemData(String itemId) {
        try
        {
            itemRepository.deleteById(itemId);
            return "Item was deleted!";
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return null;
        }
    }
}
