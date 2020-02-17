package com.innso.customertracking.controller;

import static com.innso.customertracking.controller.CustomerMessageController.CUSTOMER_MESSAGE_PATH;

import com.innso.customertracking.entity.CustomerMessage;
import com.innso.customertracking.service.CustomerMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = CUSTOMER_MESSAGE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CustomerMessageController {

  public static final String CUSTOMER_MESSAGE_PATH = "/customerMessage";

  private final CustomerMessageService customerMessageService;

  @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public CustomerMessage createMessage(@RequestBody CustomerMessage customerMessage) {
    return customerMessageService.saveCustomerMessage(customerMessage);
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CustomerMessage retrieveMessage(@PathVariable("id") long id) {
    return customerMessageService.findById(id);
  }


}
