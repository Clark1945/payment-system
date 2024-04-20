package com.example.payment_system;

import com.example.payment_system.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.HashMap;

@SpringBootTest
class PaymentSystemApplicationTests {

    MemberService memberService = new MemberService();

    @Test
    void contextLoads() {
        int errorCount = 0;
        HashMap<String,String> request = new HashMap<>();
        request.put("name","Tom");
        request.put("phone","0976268135");
        request.put("mail","01847613@gmail.com");
        request.put("pwd","123456789");

        try {
            memberService.registerFilter(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorCount += 1;
        }
        Assert.isTrue(errorCount == 0,"意外錯誤發生，共" + errorCount + "件。");
    }

    @Test
    void contextLoads2() { // name不可為""
        int errorCount = 0;
        HashMap<String,String> request = new HashMap<>();
        request.put("name","");
        request.put("phone","0976268135");
        request.put("mail","01847613@gmail.com");
        request.put("pwd","123456789");

        try {
            memberService.registerFilter(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorCount += 1;
        }
        Assert.isTrue(errorCount == 1,"意外錯誤發生，共" + errorCount + "件。");
    }

    @Test
    void contextLoads3() { // name不可>20
        int errorCount = 0;
        HashMap<String,String> request = new HashMap<>();
        request.put("name","AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        request.put("phone","0976268135");
        request.put("mail","01847613@gmail.com");
        request.put("pwd","123456789");

        try {
            memberService.registerFilter(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorCount += 1;
        }
        Assert.isTrue(errorCount == 1,"意外錯誤發生，共" + errorCount + "件。");
    }

    @Test
    void contextLoads4NameNull() { // name不可為null
        int errorCount = 0;
        HashMap<String,String> request = new HashMap<>();
        request.put("phone","0976268135");
        request.put("mail","01847613@gmail.com");
        request.put("pwd","123456789");

        try {
            memberService.registerFilter(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorCount += 1;
        }
        Assert.isTrue(errorCount == 1,"意外錯誤發生，共" + errorCount + "件。");
    }

    @Test
    void contextLoads5Phone() {// phone 不可包含特殊符號
        int errorCount = 0;
        HashMap request = new HashMap<>();
        request.put("name","Tom");
        request.put("phone","@0976-268-135");
        request.put("mail","01847613@gmail.com");
        request.put("pwd","123456789");

        try {
            memberService.registerFilter(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorCount += 1;
        }
        Assert.isTrue(errorCount == 1,"意外錯誤發生，共" + errorCount + "件。");
    }

    @Test
    void contextLoads6Mail() { // mail 必須有格式正常
        int errorCount = 0;
        HashMap request = new HashMap<>();
        request.put("name","Tom");
        request.put("phone","0976268135");
        request.put("mail","01847613gmail.com");
        request.put("pwd","123456789");

        try {
            memberService.registerFilter(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorCount += 1;
        }
        Assert.isTrue(errorCount == 1,"意外錯誤發生，共" + errorCount + "件。");
    }

    @Test
    public void contextLoads7Pwd() { // mail 必須有格式正常
        int errorCount = 0;
        HashMap request = new HashMap<>();
        request.put("name","Tom");
        request.put("phone","0976268135");
        request.put("mail","01847613@gmail.com");
        request.put("pwd","1234");

        try {
            memberService.registerFilter(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorCount += 1;
        }
        Assert.isTrue(errorCount == 1,"意外錯誤發生，共" + errorCount + "件。");
    }

    @Test
    public void contextLoads8PwdLength() { // mail 必須有格式正常
        int errorCount = 0;
        HashMap request = new HashMap<>();
        request.put("name","Tom");
        request.put("phone","0976268135");
        request.put("mail","01847613@gmail.com");
        request.put("pwd","11111111111");

        Assert.isTrue(!memberService.checkIfStrongPwd((String) request.get("pwd")),"比對錯誤");

        request.replace("pwd","AAAAAAAAAA");

        Assert.isTrue(!memberService.checkIfStrongPwd((String) request.get("pwd")),"比對錯誤");

        request.replace("pwd","aaaaaaaaaa");

        Assert.isTrue(!memberService.checkIfStrongPwd((String) request.get("pwd")),"比對錯誤");

        request.replace("pwd","aaaaaAAAAaaaaa");

        Assert.isTrue(!memberService.checkIfStrongPwd((String) request.get("pwd")),"比對錯誤");

        request.replace("pwd","Aa42567986@");

        Assert.isTrue(memberService.checkIfStrongPwd((String) request.get("pwd")),"比對錯誤");

    }
}
