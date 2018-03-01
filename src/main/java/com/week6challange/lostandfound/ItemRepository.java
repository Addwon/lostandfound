package com.week6challange.lostandfound;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface ItemRepository extends CrudRepository<Item,Long> {

Item findByFound(boolean isfound);
//Item findByItemTitleIsLike(String string);
//List<Item> findByItemTitleContainingIgnoreCaseOrItemTitleStartingWithOrItemTitleEndingWith(String string);
//List<Item>findByItemTitleStartingWithOrItemTitleEndingWithOrItemTitleContainingIgnoreCase(String string);
   //List<Item>  findByItemTitleStartingWith(String string);
   List<Item>  findByItemTitleEndingWith(String string);
//    Item findByUserIn(Collection<User> users);
}
