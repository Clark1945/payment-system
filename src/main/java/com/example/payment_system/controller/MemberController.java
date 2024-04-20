package com.example.payment_system.controller;

import com.example.payment_system.orm.PaymentMemberInfo;
import com.example.payment_system.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/member")
    public List<PaymentMemberInfo> getAllMember() {
        System.out.println("Start get All member info~");
        List<PaymentMemberInfo> response = memberService.getAllMember();
        System.out.println("Response = " + response.get(0));
        return response;
    }

    @PostMapping("/member")
    public boolean register(@RequestBody HashMap reqMap) throws NoSuchFieldException {
        System.out.println("Start register~");
        boolean response = memberService.register(reqMap);
        System.out.println("Response = " + response);
        return response;
    }
}
