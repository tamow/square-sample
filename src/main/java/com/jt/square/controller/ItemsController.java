package com.jt.square.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemsController {

	@GetMapping("/items")
    public String items(Model model) {
        return "items";
    }

}
