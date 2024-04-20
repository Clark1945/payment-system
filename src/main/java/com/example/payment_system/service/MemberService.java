package com.example.payment_system.service;

import com.example.payment_system.orm.PaymentMemberInfo;
import com.example.payment_system.repository.PaymentMemberInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service("MemberService")
public class MemberService {
    @Autowired
    private PaymentMemberInfoRepository pmiRepo;

    public List<PaymentMemberInfo> getAllMember() {
        return pmiRepo.findAll();
    }

    public boolean register(HashMap request) throws NoSuchFieldException {
        registerFilter(request);
        addMember(request);
        return true;
    }

    public void registerFilter(HashMap request) throws NoSuchFieldException {
        String name = (String) request.get("name");
        String phone = (String) request.get("phone");
        String mail = (String) request.get("mail");
        String pwd = (String) request.get("pwd");

        if (name == null || name.equals("") || name.length() > 20) {
            throw new NoSuchFieldException("name can not be null");
        }
        if (phone == null || phone.equals("") || phone.length() > 20 || !phone.matches("[0-9]+")) {
            throw new NoSuchFieldException("phone can not be null");
        }
        if (mail == null || mail.equals("") || mail.length() > 50 || !mail.contains("@")) {
            throw new NoSuchFieldException("mail can not be null");
        }
        boolean isFitPwdRule = checkIfStrongPwd(pwd);
        if (request.get("pwd") == null || mail.equals("") || phone.length() > 20 || phone.length() < 8 || !isFitPwdRule) {
            throw new NoSuchFieldException("password can not be null");
        }
    }

    public boolean checkIfStrongPwd(String pwd) {
        // 检查密码是否包含数字
        boolean hasDigit = false;
        for (char c : pwd.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
                break;
            }
        }
        if (!hasDigit) {
            return false;
        }

        // 检查密码是否包含小写字母
        boolean hasLowerCase = false;
        for (char c : pwd.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLowerCase = true;
                break;
            }
        }
        if (!hasLowerCase) {
            return false;
        }

        // 检查密码是否包含大写字母
        boolean hasUpperCase = false;
        for (char c : pwd.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
                break;
            }
        }
        if (!hasUpperCase) {
            return false;
        }

        // 检查密码是否包含特殊字符
        String specialChars = "!@#$%^&*()-+";
        boolean hasSpecialChar = false;
        for (char c : pwd.toCharArray()) {
            if (specialChars.contains(Character.toString(c))) {
                hasSpecialChar = true;
                break;
            }
        }
        if (!hasSpecialChar) {
            return false;
        }

        return true;
    }

    public void addMember(HashMap request) {
        String name = (String) request.get("name");
        String phone = (String) request.get("phone");
        String mail = (String) request.get("mail");
        String pwd = (String) request.get("pwd");

        PaymentMemberInfo paymentMemberInfo = PaymentMemberInfo.builder()
                .pmsMemberName(name)
                .pmiMemberPhone(phone)
                .pmiMemberEmail(mail)
                .pmiMemberpwd(pwd)
                .pmiLastloginTime(LocalDateTime.now())
                .build();
        pmiRepo.save(paymentMemberInfo);
    }
}