/*package com.example.wineshop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.HashMap;
import java.util.Map;

//lanzar pruebas en un puerto random
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class WineControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void all() {

        webTestClient.get()
                .uri("/employees")
                .exchange() //recupera la respuesta
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/hal+json")
                .expectBody()
                .jsonPath("$._embedded.employeeList.size()").isEqualTo(18) //tenemos 18 empleados
                .jsonPath("$._embedded.employeeList[0].firstName").isEqualTo("Bilbo");


    }

    @Test
    void one() {
        webTestClient.get()
                //.uri("/employees/1")
                .uri("/employees/{id}", 1)
                .exchange()//recupera la respuesta
                .expectStatus().isOk()//
                .expectHeader().valueEquals("Content-Type", "application/hal+json")
                .expectBody()
                .jsonPath("$.firstName").isEqualTo("Bilbo") //nombre del primero
                .jsonPath("$.id").isEqualTo(1) //id
                .jsonPath("$.role").isEqualTo("burglar"); //rol
    }


    @Test
    void employeeDoesnExist() {
        webTestClient.get()
                //.uri("/employees/1")
                .uri("/employees/{id}", 88)
                .exchange()//recupera la respuesta
                //.expectStatus().is4xxClientError()//404
                .expectStatus().isNotFound()
                .expectBody()
                //.equals("Could not find employee 88")
                .toString().contains("Could not find employee");
    }

    @Test
    void updateEmployee() {

        Map<String, Object> employee = new HashMap<>();
        employee.put("firstName", "Jacinto");
        employee.put("lastName", "Sanchez");

        webTestClient.put()
                .uri("/employees/{id}", 6)
                .bodyValue(employee)
                .exchange()//recupera la respuesta
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.firstName").isEqualTo("Jacinto")
                .jsonPath("$.lastName").isEqualTo("Sanchez");

    }

    @Test
    void createEmployee() {

        Employee employee=new Employee();
        employee.setFirstName("Monica");
        employee.setLastName("Garcia");

        webTestClient.post()
                .uri("/employees")
                .bodyValue(employee)
                .exchange()//recupera la respuesta
                .expectStatus().is2xxSuccessful()//
                .expectBody()
                .jsonPath("$.firstName").isEqualTo("Monica")
                .jsonPath("$.lastName").isEqualTo("Garcia");

    }
}

*/
