package com.wellsfargo.lms.repository;

import com.wellsfargo.lms.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    Item findByItemCategoryAndItemDescription(String itemCategory, String itemDescription);
    @Query(value = "SELECT DISTINCT(item_category) FROM test.item", nativeQuery = true)
    public List<String> getItemCat();
    @Query(value = "SELECT item_description FROM test.item WHERE item_category = ?1", nativeQuery = true)
    public List<String> getItemDesc(String itemCat);
}

