package com.example.wineshop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

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
            .uri("/wine")
            .exchange() //recupera la respuesta
            .expectStatus().isOk()
            .expectHeader().valueEquals("Content-Type", "application/hal+json")
            .expectBody()
            .jsonPath("$.name").isEqualTo("Vina El Pison") //nombre del primer vino
            .jsonPath("$.id").isEqualTo(2) //id
            .jsonPath("$.year").isEqualTo("2018") //año
            .jsonPath("$.rating").isEqualTo("4.9") //rating
            .jsonPath("$.num_reviews").isEqualTo(31) //num_reviews
            .jsonPath("$.price").isEqualTo("313.5") //price
            .jsonPath("$.body").isEqualTo("4") //body
            .jsonPath("$.acidity").isEqualTo("2") //acidity
            .jsonPath("$.winery_id").isEqualTo(2) //winery_id
            .jsonPath("$.type_id").isEqualTo(2) //type_id
            .jsonPath("$.region_id").isEqualTo(2); //region_id


            }


    @Test
    void one() {
        webTestClient.get()
                .uri("/wine/{id}", 2)
                .exchange()//recupera la respuesta
                .expectStatus().isOk()//
                .expectHeader().valueEquals("Content-Type", "application/hal+json")
                .expectBody()
                .jsonPath("$.name").isEqualTo("Vina El Pison") //nombre del primer vino
                .jsonPath("$.id").isEqualTo(2) //id
                .jsonPath("$.year").isEqualTo("2018") //año
                .jsonPath("$.rating").isEqualTo("4.9") //rating
                .jsonPath("$.num_reviews").isEqualTo(31) //num_reviews
                .jsonPath("$.price").isEqualTo("313.5") //price
                .jsonPath("$.body").isEqualTo("4") //body
                .jsonPath("$.acidity").isEqualTo("2") //acidity
                .jsonPath("$.winery_id").isEqualTo(2) //winery_id
                .jsonPath("$.type_id").isEqualTo(2) //type_id
                .jsonPath("$.region_id").isEqualTo(2); //region_id
    }


    @Test
    void wineDoesnExist() {
        webTestClient.get()

                .uri("/wine/{id}", 8000)
                .exchange()//recupera la respuesta
                //.expectStatus().is4xxClientError()//404
                .expectStatus().isNotFound()
                .expectBody()
                .toString().contains("Could not find wine");
    }


    @Test
    void updateWine() {

        Wine wine = new Wine();
        wine.setName("Monica");
        wine.setYear("2050");
        wine.setRating(5.3f);

        webTestClient.put()
                .uri("/wine/{id}", 1)
                .bodyValue(wine)
                .exchange()//recupera la respuesta
                .expectStatus().is2xxSuccessful()
                .expectBody()
                //.jsonPath("$.name").isEqualTo("Monica")
                .jsonPath("$.year").isEqualTo("2050");

    }


    @Test
    void createWine() {
        Wine wine = new Wine();
        wine.setName("Tardillo");
        wine.setYear("1800");


        webTestClient.post()
                .uri("/wine")
                .bodyValue(wine)
                .exchange()//recupera la respuesta
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Tardillo")
                .jsonPath("$.year").isEqualTo("1800");

    }



    @Test
    void deleteWine(){

        webTestClient.delete()
                .uri("/wine/{id}", 7501)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    void deleteWineDoesnExists(){
        webTestClient.delete()
                .uri("/wine/{id}", 7501)
                .exchange()
                .expectStatus().is5xxServerError();

    }

}




