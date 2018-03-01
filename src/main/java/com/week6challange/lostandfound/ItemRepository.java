package com.week6challange.lostandfound;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item,Long> {
//    Item findByItemType(String itemName);
//    Item findByItemNameContainingIgnoreCase(String searchText);
}
