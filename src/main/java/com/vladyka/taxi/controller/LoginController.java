package com.vladyka.taxi.controller;

import com.vladyka.taxi.dto.UserDTO;
import com.vladyka.taxi.model.User;
import com.vladyka.taxi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/", "/login"})
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping(value = "/admin/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("admin/home");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.findUserByUserName(auth.getName());

        UserDTO userDTO = new UserDTO(user);

        modelAndView.addObject(
                "userName", "Welcome " + userDTO);
        modelAndView.addObject("adminMessage",
                "Content Available Only for Users with Admin Role");

        return modelAndView;
    }

    @GetMapping(value = "/main")
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView();


        return modelAndView;
    }

}
