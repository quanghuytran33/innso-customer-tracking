package com.innso.customertracking.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.innso.customertracking.AbstractCustomerTrackingControllerIntegrationTest;
import com.innso.customertracking.entity.CustomerMessage;
import com.innso.customertracking.enumeration.EChannel;
import javax.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class CustomerMessageControllerTest extends
    AbstractCustomerTrackingControllerIntegrationTest {

  @PostConstruct
  public void init() {
    super.init();
    uri += CustomerMessageController.CUSTOMER_MESSAGE_PATH;
  }

  @Test
  public void create_MessageFromJeremieDurand_MessageWithId() throws JsonProcessingException {

    String sender = "Jeremie Durant";
    String content = "Bonjour, j’ai un problème avec mon nouveau téléphone";
    CustomerMessage message = CustomerMessage.builder()
        .sender(sender).content(content)
        .channel(EChannel.MAIL).build();

    // @formatter:off
    given()
        .body(mapper.writeValueAsString(message))
        .contentType(MediaType.APPLICATION_JSON_VALUE)
    .when()
        .post(uri + "/create")
    .then()
        .assertThat()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", notNullValue())
        .body("sender", equalTo(sender))
        .body("content", equalTo(content))
    ;
    // @formatter:on
  }

}