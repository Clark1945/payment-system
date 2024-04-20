package com.example.payment_system.orm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_member_info")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMemberInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pmi_member_id")
    @Getter
    private Long pmiMeberId;
    @Getter
    @Column(name = "pmi_member_name",nullable = false,length = 255)
    private String pmsMemberName;
    @Getter
    @Column(name = "pmi_member_phone",nullable = false,length = 30)
    private String pmiMemberPhone;
    @Getter
    @Column(name = "pmi_member_mail",nullable = false,length = 255)
    private String pmiMemberEmail;
    @Getter
    @Column(name = "pmi_member_pwd",nullable = false,length = 255)
    private String pmiMemberpwd;
    @Getter
    @Column(name = "pmi_lastlogin_time",nullable = false)
    private LocalDateTime pmiLastloginTime;

}
