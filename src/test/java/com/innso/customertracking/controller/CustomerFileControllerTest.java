package com.innso.customertracking.controller;

import static com.innso.customertracking.controller.CustomerFileController.CUSTOMER_FILE_PATH;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.innso.customertracking.AbstractCustomerTrackingControllerIntegrationTest;
import com.innso.customertracking.entity.CustomerFile;
import com.innso.customertracking.entity.CustomerMessage;
import com.innso.customertracking.enumeration.EChannel;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
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
        .customerMessages(Sets.newSet(message)).build();

    // @formatter:off
    int id =
              given()
                  .body(mapper.writeValueAsString(customerFile))
                  .contentType(MediaType.APPLICATION_JSON_VALUE)
              .when()
                  .post(uri + CUSTOMER_FILE_PATH + "/create")
              .then()
                  .assertThat()
                  .statusCode(HttpStatus.CREATED.value())
                  .extract().path("id");

    CustomerFile actual =
        given()
          .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
          .get(uri + CUSTOMER_FILE_PATH + "/" + id)
        .then()
          .assertThat()
          .statusCode(HttpStatus.OK.value())
          .extract().as(CustomerFile.class);

    assertEquals(actual.getClient(), sender);
    assertEquals(actual.getCustomerMessages().iterator().next().getSender(), sender);
    assertEquals(actual.getCustomerMessages().iterator().next().getContent(), content);
    // @formatter:on
  }

  @Test
  public void updateCustomerFile_NewReference_FileWithReference()
      throws JsonProcessingException {

    CustomerFile customerFile = CustomerFile.builder().client("test").build();

    // @formatter:off
    int id =
        given()
            .body(mapper.writeValueAsString(customerFile))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post(uri + CUSTOMER_FILE_PATH + "/create")
            .then()
            .assertThat()
            .statusCode(HttpStatus.CREATED.value())
            .extract().path("id");

    String expectedReference = "AK-reference";
    customerFile.setReference(expectedReference);

    String actualReference =
        given()
            .body(mapper.writeValueAsString(customerFile))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
            .put(uri + CUSTOMER_FILE_PATH + "/" + id)
        .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .extract().path("reference");

    assertEquals(expectedReference, actualReference);
    // @formatter:on
  }


}