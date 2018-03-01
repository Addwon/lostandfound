package com.week6challange.lostandfound;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ItemRepository extends CrudRepository<Item,Long> {
//    Item findByItemType(String itemName);
//    Item findByItemNameContainingIgnoreCase(String searchText);
    //Item findByFoundAndUname(boolean found,String username);
Item findByFound(boolean isfound);

//    Item findByUserIn(Collection<User> users);
}
