package com.example.payment_system.repository;

import com.example.payment_system.orm.PaymentMemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMemberInfoRepository extends JpaRepository<PaymentMemberInfo, Long> {
    List<PaymentMemberInfo> getPaymentMemberInfosByPmiMemberEmail(String mail);
}
