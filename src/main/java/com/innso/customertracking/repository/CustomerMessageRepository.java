package com.innso.customertracking.repository;

import com.innso.customertracking.entity.CustomerMessage;
import org.springframework.data.repository.CrudRepository;

public interface CustomerMessageRepository extends CrudRepository<CustomerMessage, Long> {

}
