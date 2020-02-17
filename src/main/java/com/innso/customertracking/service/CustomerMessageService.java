package com.innso.customertracking.service;

import com.innso.customertracking.entity.CustomerMessage;
import com.innso.customertracking.exception.ResourceNotFoundException;
import com.innso.customertracking.repository.CustomerMessageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMessageService {

  private final CustomerMessageRepository customerMessageRepository;

  public CustomerMessage saveCustomerMessage(CustomerMessage customerMessage) {
    return customerMessageRepository.saveAndFlush(customerMessage);
  }

  public CustomerMessage findById(long id) {
    return customerMessageRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException(CustomerMessage.class.getName(), String.valueOf(id)));
  }

  public List<CustomerMessage> findAll() {
    return customerMessageRepository.findAll();
  }

}
