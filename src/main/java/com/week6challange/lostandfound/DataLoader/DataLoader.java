package com.week6challange.lostandfound.DataLoader;
import com.week6challange.lostandfound.Configuration.EmailService;
import com.week6challange.lostandfound.Model.Item;
import com.week6challange.lostandfound.Model.Mail;
import com.week6challange.lostandfound.Model.Role;
import com.week6challange.lostandfound.Model.User;
import com.week6challange.lostandfound.Repositories.ItemRepository;
import com.week6challange.lostandfound.Repositories.RoleRepository;
import com.week6challange.lostandfound.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ItemRepository itemRepository;
 //Mail
 //private static Logger log = LoggerFactory.getLogger(Application.class);

// @Autowired
// private EmailService emailService;

/*
 public static void main(String[] args) throws Exception {
  SpringApplication.run(Application.class, args);
 }
*/

    @Override
    public void run(String... strings) throws Exception{
       System.out.println("Loading data . . .");
       roleRepository.save(new Role("USER"));
       roleRepository.save(new Role("ADMIN"));

        Role adminRole=roleRepository.findByRole("ADMIN");
        Role userRole=roleRepository.findByRole("USER");

        User user1=new User("admin@admin.com","password","Dave","Wolf",true,"Dave");
        user1.setRoles(Arrays.asList(adminRole));
        userRepository.save(user1);

        User user2=new User("user@user.com","password","Addis","Wondie",true,"Addis");
        user2.setRoles(Arrays.asList(userRole));
        userRepository.save(user2);

        User user3 = new User("bob@bob.com", "password", "Bob", "Marley", true, "Bob");
        user3.setRoles(Arrays.asList(userRole));
        userRepository.save(user3);

        //Item 1
        Item item1=new Item();
        item1.setItemTitle("Cell phone");
        item1.setItemCatgory("other");
        item1.setItemDescription("iPhone 7, white color");
        item1.setFound(false);
        item1.setImgUrl("https://support.apple.com/library/APPLE/APPLECARE_ALLGEOS/SP743/iphone7-gold.png");
        item1.setUser(user2);
        //item1.setUname(user2.getFirstName());
        itemRepository.save(item1);

        //Item 2
        Item item2=new Item();
        item2.setItemTitle("Jacket");
        item2.setItemCatgory("clothes");
        item2.setItemDescription("Large size,red color");
        item2.setFound(false);
        item2.setImgUrl("https://cdn.shopify.com/s/files/1/0312/6537/products/6785-PRX_1024x1024.jpeg?v=1476969075");
        item2.setUser(user3);
        itemRepository.save(item2);

        //Item 3
        Item item3=new Item();
        item3.setItemTitle("dog");
        item3.setItemCatgory("Pets");
        item3.setItemDescription("Brown bulldog");
        item3.setFound(false);
        item3.setImgUrl("https://www.dogbreedinfo.com/images22/ValleyBulldogPuppiesEnglishBulldogBoxerHybridLucy.jpg");
        item3.setUser(user3);
        itemRepository.save(item3);

        //Item 4
        Item item4=new Item();
        item4.setItemTitle("Hat");
        item4.setItemCatgory("clothes");
        item4.setItemDescription("Black fedora");
        item4.setFound(false);
        item4.setImgUrl("https://images-na.ssl-images-amazon.com/images/I/61UQVDjpXRL._UX466_.jpg");
        item4.setUser(user2);
        itemRepository.save(item4);

        //Item 5
        Item item5=new Item();
        item5.setItemTitle("Wallet");
        item5.setItemCatgory("other");
        item5.setItemDescription("Brown wallet with credit cards and driving licence");
        item5.setFound(false);
        item5.setImgUrl("https://i.pinimg.com/736x/52/34/7a/52347a1b23e3cababcc547e9b559e03f--brown-leather-wallet-leather-men.jpg");
        item5.setUser(user3);
        itemRepository.save(item5);

    /* //log.info("Spring Mail - Sending Simple Email with JavaMailSender Example");
     System.out.println("Sending email...");
     Mail mail = new Mail();
     mail.setFrom("no-reply@memorynotfound.com");
     mail.setTo("addwon@gmail.com");
     mail.setSubject("Sending Simple Email with JavaMailSender Example");
     mail.setContent("This tutorial demonstrates how to send a simple email using Spring Framework.");

     emailService.sendSimpleMessage(mail);*/
    }
}
