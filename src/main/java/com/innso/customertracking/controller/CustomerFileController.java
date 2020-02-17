package com.innso.customertracking.controller;


import com.innso.customertracking.entity.CustomerFile;
import com.innso.customertracking.service.CustomerFileService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = CustomerFileController.CUSTOMER_FILE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CustomerFileController {

  public static final String CUSTOMER_FILE_PATH = "/customerFile";

  private final CustomerFileService customerFileService;

  @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public CustomerFile createCustomerFile(@RequestBody CustomerFile customerFile) {
    return customerFileService.saveCustomerFile(customerFile);
  }

  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CustomerFile updateCustomerFile(
      @PathVariable("id") long id,
      @RequestBody CustomerFile customerFile) {
    return customerFileService.updateById(id, customerFile);
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CustomerFile retrieveCustomerFile(@PathVariable("id") long id) {
    return customerFileService.retrieveCustomerFileById(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<CustomerFile> retrieveAllCustomerFile() {
    return customerFileService.retrieveAllCustomerFile();
  }

}
