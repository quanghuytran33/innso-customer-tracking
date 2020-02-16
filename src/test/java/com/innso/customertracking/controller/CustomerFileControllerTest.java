package com.innso.customertracking.controller;

import static com.innso.customertracking.controller.CustomerFileController.CUSTOMER_FILE_PATH;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.innso.customertracking.AbstractCustomerTrackingControllerIntegrationTest;
import com.innso.customertracking.entity.CustomerFile;
import com.innso.customertracking.entity.CustomerMessage;
import com.innso.customertracking.enumeration.EChannel;
import java.util.Set;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class CustomerFileControllerTest extends AbstractCustomerTrackingControllerIntegrationTest {

  @Test
  public void createCustomerFile_FileWithJeremieDurandAsClientAndItsMessage_FileWithMessage()
      throws JsonProcessingException {
    String sender = "Jeremie Durant";
    String content = "Bonjour, j’ai un problème avec mon nouveau téléphone";
    CustomerMessage message = CustomerMessage.builder()
        .sender(sender).content(content)
        .channel(EChannel.MAIL).build();

    CustomerFile customerFile = CustomerFile.builder().client(sender)
        .customerMessages(Set.of(message)).build();

    // @formatter:off
    given()
        .body(mapper.writeValueAsString(customerFile))
        .contentType(MediaType.APPLICATION_JSON_VALUE)
    .when()
        .post(uri + CUSTOMER_FILE_PATH + "/create")
    .then()
        .assertThat()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", notNullValue())
        .body("client", equalTo(sender))
        .body("message[0].sender", equalTo(sender))
        .body("message[0].content", equalTo(content))
    ;
    // @formatter:on
  }


}