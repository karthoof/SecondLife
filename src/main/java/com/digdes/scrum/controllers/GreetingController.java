package com.digdes.scrum.controllers;

import com.digdes.scrum.model.entity.User;
import com.digdes.scrum.model.enums.BusyStatus;
import com.digdes.scrum.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @RequestMapping("/greeting")
    public String greeting(String name, Model model) {

        User user = new User();
        user.setStatus(BusyStatus.ACTIVE);
        user.setRoles(Role.DEVELOPER);
        user.setName("Solat");
        user.setLastName("Pelmen");

        name = user.getName();
        model.addAttribute("name", name);

        return "greeting";
    }

}