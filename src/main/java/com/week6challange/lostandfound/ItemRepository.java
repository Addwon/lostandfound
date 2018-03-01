package com.week6challange.lostandfound;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ItemRepository extends CrudRepository<Item,Long> {
//    Item findByItemType(String itemName);
//    Item findByItemNameContainingIgnoreCase(String searchText);
    Item findByFound(boolean found);
//    Item findByUserIn(Collection<User> users);
}
