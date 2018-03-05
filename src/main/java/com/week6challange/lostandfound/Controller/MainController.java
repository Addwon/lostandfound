package com.week6challange.lostandfound.Controller;

import com.week6challange.lostandfound.Model.Item;
import com.week6challange.lostandfound.Model.Role;
import com.week6challange.lostandfound.Model.User;
import com.week6challange.lostandfound.Repositories.ItemRepository;
import com.week6challange.lostandfound.Repositories.RoleRepository;
import com.week6challange.lostandfound.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping("/login")
    public String login(){return "login";}

    @RequestMapping("/")
    public String showIndex(Model model)
    {
        model.addAttribute("item",itemRepository.findAll());
        return "index";
    }

    //User registration
    @RequestMapping(value="/registration",method= RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("user",new User());
        return "registration";
    }
    @RequestMapping(value="/registration",method= RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result,Model model){
        //model.addAttribute("user",user);
        if(result.hasErrors()){
            return "registration";
        }else{
            user.setEnabled(true);
            userRepository.save(user);
        }
        return "redirect:/";
    }

    //Report lost items
    @RequestMapping(value="/additems",method= RequestMethod.GET)
    public String showAddItemsPage(Model model){
        model.addAttribute("item",new Item());
        return "additems";
    }
    @RequestMapping(value="/additems",method= RequestMethod.POST)
    public String processAddItemsPage(@Valid @ModelAttribute("item") Item item, User user, Principal principal, BindingResult result){

        if(result.hasErrors()){
            return "additems";
        }else{

           user=userRepository.findByUsername(principal.getName());
           Role role=roleRepository.findByUsers(user);

           System.out.println("Role: "+role.getRole());

           if(role.getRole().toString().equalsIgnoreCase("ADMIN"))
               user.setFirstName("");

           item.setUser(user);
           item.setFound(false);

            if(item.getImgUrl().isEmpty()){
                item.setImgUrl("http://cdn2.sportngin.com/attachments/photo/2122/4060/70_medium.jpg");
            }
            itemRepository.save(item);
        }
        return "redirect:/";
    }

    //Display items with their status for admin
    //This is the admin view of all the items
    @RequestMapping("/itemsstatus")
    public String showItemsStatus(Model model)
    {        model.addAttribute("item",itemRepository.findAll());
        return "itemsstatus";
    }
    @RequestMapping("/change/{id}")
    public String changeItemsStatus(@PathVariable("id") long id, Model model, Item item, Role role)
    {
        item=itemRepository.findOne(id);
        if(!item.isFound()){
            item.setFound(true);
        }
        else{
            item.setFound(false);
        }
        itemRepository.save(item);
        model.addAttribute("item",itemRepository.findAll());
        return "itemsstatus";
    }

    //Display found items for current user
    @RequestMapping("/useritems")
    public String showUserItems(Model model, Principal principal,User user,Item item)
    {
        user=userRepository.findByUsername(principal.getName());
        model.addAttribute("item",itemRepository.findByUser(user));
        return "useritems";
    }

    //Admin reports missing user items
    @GetMapping("/reportitems")
    public String showReportItemsForm(Model model)
    {
        model.addAttribute("item",new Item());
        model.addAttribute("user",userRepository.findAll());
        return "reportitems";
    }
    @PostMapping("/reportitems")
    public String saveMovie(@ModelAttribute("item") Item item, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            return "reportitems";
        }
        itemRepository.save(item);
        userRepository.save(item.getUser());
        return "redirect:/itemsstatus";
    }

    //Display items by category
    @RequestMapping("/findby/{category}")
    public String changeItemsStatus(@PathVariable("category") String category, Model model, Item item)
    {
        model.addAttribute("item",itemRepository.findByItemCatgoryContains(category));
        return "index";
    }
    //Search items

    @GetMapping("/search")
    public String getSearch()
    {
        return "index";
    }

    @PostMapping("/search")
    public String showSearchResults(HttpServletRequest request, Model model)
    {
        String searchString = request.getParameter("search");
       model.addAttribute("item",itemRepository.findByItemTitleContainsOrItemCatgoryContains(searchString,searchString));
        return "index";
    }
}
