package com.innso.customertracking.repository;

import com.innso.customertracking.entity.CustomerMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerMessageRepository extends JpaRepository<CustomerMessage, Long> {

}
