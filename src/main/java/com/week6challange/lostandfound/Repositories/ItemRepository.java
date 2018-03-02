package com.week6challange.lostandfound.Repositories;

import com.week6challange.lostandfound.Model.Item;
import com.week6challange.lostandfound.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item,Long> {

Item findByFound(boolean isfound);
   //List<Item>  findByItemTitleEndingWith(String string);
   List<Item> findByUser(User user);
   List<Item>findByItemTitleContainsOrItemCatgoryContains(String title,String category);
   List<Item>findByItemCatgoryContains(String category);
}
