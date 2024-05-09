package com.example.payment_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class DemoController {

    private List<String> eachDemos = Arrays.asList(
            "1. 請閱讀個人資訊合理取用同意書",
            "2. 本站僅提供測試，不負任何法律責任",
            "3. 尚未實作金流功能，待補充",
            "4. 面試Demo用");
    @GetMapping("/hello")
    public String hello(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model) {
        model.addAttribute("name", name); // set request attribute with key "name"
        model.addAttribute("eachDemos",eachDemos);
        return "hello"; // forward to hello.html
    }

    @GetMapping("/register")
    public String addMemberPage(){
        return "addMemberPage";
    }

    @GetMapping("/login")
    public String LoginMemberPage(){
        return "loginMemberPage";
    }
}
