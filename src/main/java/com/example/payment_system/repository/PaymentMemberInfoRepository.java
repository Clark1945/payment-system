package com.example.payment_system.repository;

import com.example.payment_system.orm.PaymentMemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMemberInfoRepository extends JpaRepository<PaymentMemberInfo, Long> {
}
