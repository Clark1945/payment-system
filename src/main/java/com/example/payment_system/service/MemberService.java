package com.example.payment_system.service;

import com.example.payment_system.RegisterObject;
import com.example.payment_system.orm.PaymentMemberInfo;
import com.example.payment_system.repository.PaymentMemberInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service("MemberService")
@Slf4j
public class MemberService {

    @Autowired
    private PaymentMemberInfoRepository pmiRepo;

    public List<PaymentMemberInfo> getAllMember() {
        return pmiRepo.findAll();
    }

    public String register(HashMap<String,String> request) {
        RegisterObject registerObj = RegisterObject.createRegister(request);
        try {
            registerFilter(registerObj);
            checkIfDuplicated(registerObj);
            addMember(registerObj);
            return "Success";
        }catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            return "Fail";
        }
    }

    public void registerFilter(RegisterObject request) throws NoSuchFieldException {
        String name = request.getName();
        String phone = request.getPhone();
        String mail = request.getEmail();
        String pwd = request.getPassword();

        if (name == null || name.equals("") || name.length() > 20) {
            throw new NoSuchFieldException("name Error");
        }
        if (phone == null || phone.equals("") || phone.length() > 20 || !phone.matches("[0-9]+")) {
            throw new NoSuchFieldException("phone Error");
        }
        if (mail == null || mail.length() > 50 || !mail.contains("@")) {
            throw new NoSuchFieldException("mail Error");
        }
        boolean isFitPwdRule = checkIfStrongPwd(pwd);
        if (pwd.length() > 20 || pwd.length() < 8 || !isFitPwdRule) {
            throw new NoSuchFieldException("password Error");
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
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void checkIfDuplicated(RegisterObject request) throws NoSuchFieldException {
        String mail = request.getEmail();
        List<PaymentMemberInfo> paymentMemberInfo = pmiRepo.getPaymentMemberInfosByPmiMemberEmail(mail);

        if (paymentMemberInfo.size() > 0) {
            throw new NoSuchFieldException("帳號已存在");
        }
    }
    @Transactional
    public void addMember(RegisterObject request) {
        String name = request.getName();
        String phone = request.getPhone();
        String mail = request.getEmail();
        String pwd = request.getPassword();

        PaymentMemberInfo paymentMemberInfo = PaymentMemberInfo.builder()
                .pmsMemberName(name)
                .pmiMemberPhone(phone)
                .pmiMemberEmail(mail)
                .pmiMemberpwd(pwd)
                .pmiLastloginTime(LocalDateTime.now())
                .build();
        pmiRepo.saveAndFlush(paymentMemberInfo);
    }

    public String login(HashMap<String,String> request) {
        RegisterObject registerObject = RegisterObject.createLogin(request);
        try {
            checkIfValid(registerObject);
            return "Success";
        }catch (Exception e) {
            log.info(e.getMessage());
            return "Fail";
        }
    }

    @Transactional
    public void checkIfValid(RegisterObject request) throws NoSuchFieldException {
        String mail = request.getEmail();
        String pwd = request.getPassword();

        List<PaymentMemberInfo> paymentMemberInfo = pmiRepo.getPaymentMemberInfosByPmiMemberEmail(mail);
        if (paymentMemberInfo.size() == 0) {
            throw new NoSuchFieldException("帳號不存在");
        }
        if (!paymentMemberInfo.get(0).getPmiMemberpwd().equals(pwd)) {
            throw new NoSuchFieldException("密碼錯誤");
        }
    }
}
