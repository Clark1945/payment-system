package com.example.payment_system.orm;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Getter
public class PaymentMemberInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pmi_member_id")
    @NotNull
    private Long pmiMeberId;
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "pmi_member_name",nullable = false)
    private String pmsMemberName;
    @NotNull
    @Column(name = "pmi_member_phone",nullable = false,length = 30)
    private String pmiMemberPhone;
    @NotNull
    @Column(name = "pmi_member_mail",nullable = false,length = 255)
    private String pmiMemberEmail;
    @NotNull
    @Column(name = "pmi_member_pwd",nullable = false,length = 255)
    private String pmiMemberpwd;
    @NotNull
    @Column(name = "pmi_lastlogin_time",nullable = false)
    private LocalDateTime pmiLastloginTime;

}
