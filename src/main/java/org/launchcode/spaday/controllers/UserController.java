package org.launchcode.spaday.controllers;

import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {


    @GetMapping("index")
    public String displayIndexUserForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "user/index";
    }
    @GetMapping("add")
    public String displayAddUserForm(){
        return "user/add";
    }

    @PostMapping("add")
    public String processAddUserForm(Model model, @ModelAttribute User user, @RequestParam String verify, HttpSession session) {
        // add form submission handling code here
        if (user.getPassword().equals(verify)) {
            model.addAttribute(user);
            session.setAttribute("user", user);
            return "redirect:index";
        }
        return "user/add";
    }
}
