package com.example.payment_system;

import com.example.payment_system.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest()
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class PaymentSystemApplicationTests {

    MemberService memberService = new MemberService();

    @Test
    void contextLoads() {
        int errorCount = 0;
        HashMap<String,String> request = new HashMap<>();
        request.put("name","Tom");
        request.put("phone","0976268135");
        request.put("email","01847613@gmail.com");
        request.put("password","123456a$7A89");

        RegisterObject registerObject = RegisterObject.createRegister(request);
        try {
            memberService.registerFilter(registerObject);
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
        request.put("email","01847613@gmail.com");
        request.put("password","123456789");

        RegisterObject registerObject = RegisterObject.createRegister(request);
        try {
            memberService.registerFilter(registerObject);
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
        request.put("email","01847613@gmail.com");
        request.put("password","123456789");

        RegisterObject registerObject = RegisterObject.createRegister(request);
        try {
            memberService.registerFilter(registerObject);
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
        request.put("email","01847613@gmail.com");
        request.put("password","123456789");

        RegisterObject registerObject = RegisterObject.createRegister(request);
        try {
            memberService.registerFilter(registerObject);
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
        request.put("email","01847613@gmail.com");
        request.put("password","123456789");

        RegisterObject registerObject = RegisterObject.createRegister(request);
        try {
            memberService.registerFilter(registerObject);
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
        request.put("email","01847613gmail.com");
        request.put("password","123456789");

        RegisterObject registerObject = RegisterObject.createRegister(request);
        try {
            memberService.registerFilter(registerObject);
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
        request.put("email","01847613@gmail.com");
        request.put("password","1234");

        RegisterObject registerObject = RegisterObject.createRegister(request);
        try {
            memberService.registerFilter(registerObject);
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
        request.put("email","01847613@gmail.com");
        request.put("password","11111111111");

        Assert.isTrue(!memberService.checkIfStrongPwd((String) request.get("password")),"比對錯誤");

        request.replace("password","AAAAAAAAAA");

        Assert.isTrue(!memberService.checkIfStrongPwd((String) request.get("password")),"比對錯誤");

        request.replace("password","aaaaaaaaaa");

        Assert.isTrue(!memberService.checkIfStrongPwd((String) request.get("password")),"比對錯誤");

        request.replace("password","aaaaaAAAAaaaaa");

        Assert.isTrue(!memberService.checkIfStrongPwd((String) request.get("password")),"比對錯誤");

        request.replace("password","Aa42567986@");

        Assert.isTrue(memberService.checkIfStrongPwd((String) request.get("password")),"比對錯誤");

    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void ifObjectTransmitted() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");
        httpHeaders.add("ClientIp","1.1.1.1");
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .get("/api/member")
                        .headers(httpHeaders)
                        .param("member_token","001");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
        System.out.print(result);

    }
}
