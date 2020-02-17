package com.innso.customertracking.repository;

import com.innso.customertracking.entity.CustomerFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerFileRepository extends JpaRepository<CustomerFile, Long> {

}
