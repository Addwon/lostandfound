package com.week6challange.lostandfound.Repositories;

import com.week6challange.lostandfound.Model.Item;
import com.week6challange.lostandfound.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item,Long> {

Item findByFound(boolean isfound);
//Item findByItemTitleIsLike(String string);
//List<Item> findByItemTitleContainingIgnoreCaseOrItemTitleStartingWithOrItemTitleEndingWith(String string);
//List<Item>findByItemTitleStartingWithOrItemTitleEndingWithOrItemTitleContainingIgnoreCase(String string);
   //List<Item>  findByItemTitleStartingWith(String string);
   List<Item>  findByItemTitleEndingWith(String string);
   List<Item> findByUser(User user);

//    Item findByUserIn(Collection<User> users);
}
