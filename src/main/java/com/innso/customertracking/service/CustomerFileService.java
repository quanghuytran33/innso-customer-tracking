package com.innso.customertracking.service;

import com.innso.customertracking.entity.CustomerFile;
import com.innso.customertracking.exception.ResourceNotFoundException;
import com.innso.customertracking.repository.CustomerFileRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerFileService {

  private final CustomerFileRepository customerFileRepository;

  public CustomerFile saveCustomerFile(CustomerFile customerFile) {
    return customerFileRepository.saveAndFlush(customerFile);
  }

  public CustomerFile retrieveCustomerFileById(long id) {
    return customerFileRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException(CustomerFile.class.getName(), String.valueOf(id)));
  }

  public CustomerFile updateById(long id, CustomerFile customerFile) {
    if (customerFileRepository.existsById(id)) {
      customerFile.setId(id);
      return customerFileRepository.saveAndFlush(customerFile);
    } else {
      throw new ResourceNotFoundException(CustomerFile.class.getName(), String.valueOf(id));
    }
  }

  public List<CustomerFile> retrieveAllCustomerFile() {
    return customerFileRepository.findAll();
  }
}
