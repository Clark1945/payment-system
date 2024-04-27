package com.example.payment_system.controller;

import com.example.payment_system.orm.PaymentMemberInfo;
import com.example.payment_system.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Tag(name = "get", description = "GET methods of Payment APIs")
    @Operation(summary = "取得當前所有會員資訊",
            description = "取得所有會員資料，" +
                        "  限定Admin使用者使用（規劃開發中）")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PaymentMemberInfo.class)) }),
            @ApiResponse(responseCode = "500", description = "System Error",
                    content = @Content) })
    @GetMapping("/member")
    public List<PaymentMemberInfo> getAllMember(
            @RequestParam(value = "member_token",required = false) String token) {
        System.out.println("Start get All member info~");
        List<PaymentMemberInfo> response = memberService.getAllMember();
        System.out.println("Response = " + response.get(0));
        return response;
    }

    @Tag(name = "post", description = "POST methods of Payment APIs")
    @Operation(summary = "會員註冊API",
            description = "判斷輸入格式是否異常，如無異常就新增資料庫。" +
                    "資料須符合以下條件：\n " +
                    "1. name,phone,mail,pwd 不可為空。\n" +
                    "2. 除mail以外，長度不可以超過20個字元。mail長度不可超過50個字元。\n" +
                    "3. 密碼規則必須要包含數字、大小寫字母以及符號。\n")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentMemberInfo.class)) }),
            @ApiResponse(responseCode = "404", description = "API Not Exists",
                    content = @Content) })
    @PostMapping("/member")
    public String register(
            @Parameter(
                    description = "request content from web",
                    required = true)
            @RequestBody HashMap reqMap) {
        System.out.println("Start register~");
        String response = memberService.register(reqMap);
        System.out.println("Response = " + response);
        return response;
    }

    @Tag(name = "put", description = "PUT methods of Payment APIs")
    @Operation(summary = "會員登入API",
            description = "判斷登入帳號是否存在，如無就回復錯誤")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentMemberInfo.class)) }),
            @ApiResponse(responseCode = "404", description = "API Not Exists",
                    content = @Content) })
    @PutMapping("/member")
    public String login(@RequestBody HashMap reqMap) {
        System.out.println("Start login~");
        String response = memberService.login(reqMap);
        System.out.println("Response = " + response);
        return response;
    }
}
