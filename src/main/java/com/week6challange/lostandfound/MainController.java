package com.week6challange.lostandfound;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    {        model.addAttribute("item",itemRepository.findAll());
        return "index";
    }

    //User registration
    @RequestMapping(value="/registration",method= RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("user",new User());
        return "registration";
    }
    @RequestMapping(value="/registration",method= RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user,Role role, BindingResult result){

        if(result.hasErrors()){
            return "registration";
        }else{
            /*role=roleRepository.findByRole("USER");
            UserService userService;
            userService.saveUser(user);
            user.setRoles(role);*/
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
            item.setUser(user);
            item.setFound(false);
            itemRepository.save(item);
        }
        return "redirect:/";
    }

    //Display items with their status for admin
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

}
