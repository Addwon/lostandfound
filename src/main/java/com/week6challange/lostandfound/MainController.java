package com.week6challange.lostandfound;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result){

        if(result.hasErrors()){
            return "registration";
        }else{
            userRepository.save(user);
        }
        return "redirect:/";
    }

    //Add items
    @RequestMapping(value="/additems",method= RequestMethod.GET)
    public String showAddItemsPage(Model model){
        model.addAttribute("item",new Item());
        return "additems";
    }
    @RequestMapping(value="/additems",method= RequestMethod.POST)
    public String processAddItemsPage(@Valid @ModelAttribute("item") Item item, BindingResult result){

        if(result.hasErrors()){
            return "additems";
        }else{
            itemRepository.save(item);
        }
        return "redirect:/";
    }
}
