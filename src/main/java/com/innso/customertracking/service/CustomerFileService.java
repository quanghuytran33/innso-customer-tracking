package com.innso.customertracking.service;

import com.innso.customertracking.entity.CustomerFile;
import com.innso.customertracking.exception.ResourceNotFoundException;
import com.innso.customertracking.repository.CustomerFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerFileService {

  private final CustomerFileRepository customerFileRepository;

  public CustomerFile saveCustomerFile(CustomerFile customerFile) {
    return customerFileRepository.save(customerFile);
  }

  public CustomerFile retrieveCustomerFileById(long id) {
    return customerFileRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException(CustomerFile.class.getName(), String.valueOf(id)));
  }
}
