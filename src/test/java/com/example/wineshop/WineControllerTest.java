package com.example.wineshop;

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
/*
    @Test
   void all() {

        webTestClient.get()
                .uri("/wine")
                .exchange() //recupera la respuesta
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/hal+json")
                .expectBody()
                .jsonPath("$._embedded.wineList.size()").isEqualTo(500) //tenemos 18 empleados
                .jsonPath("$._embedded.wineList[0].name").isEqualTo("Tinto");


    }*/

    @Test
    void one() {
        webTestClient.get()
                .uri("/wine/{id}", 1)
                .exchange()//recupera la respuesta
                .expectStatus().isOk()//
                .expectHeader().valueEquals("Content-Type", "application/hal+json")
                .expectBody()
                .jsonPath("$.name").isEqualTo("Tinto") //nombre del primero
                .jsonPath("$.id").isEqualTo(1) //id
                .jsonPath("$.year").isEqualTo("2013"); //rol
    }




    @Test
    void wineDoesnExist() {
        webTestClient.get()
                .uri("/wine/{id}", 8000)
                .exchange()//recupera la respuesta
                //.expectStatus().is4xxClientError()//404
                .expectStatus().isNotFound()
                .expectBody()
                //.equals("Could not find employee 88")
                .toString().contains("Could not find wine");
    }


/*
    @Test
    void updateWine() {

        Wine employee=new Wine(50, int winery_id, String name, String year, int num_reviews, float rating, int region_id, double price, int type_id, String body, String acidity);
        employee.setFirstName("Monica");
        employee.setLastName("Garcia");

        webTestClient.put()
                .uri("/employees/{id}", 6)
                .bodyValue(employee)
                .exchange()//recupera la respuesta
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.firstName").isEqualTo("Jacinto")
                .jsonPath("$.lastName").isEqualTo("Sanchez");

    }
*/
    /*
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
    */

}

