package com.pvt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcomePage(ModelMap model) {
            model.put("greeting", "user");
        return "welcome";
    }

    @GetMapping(value = "/statistics")
    public String statistics() {
        return "statistics";
    }
}



