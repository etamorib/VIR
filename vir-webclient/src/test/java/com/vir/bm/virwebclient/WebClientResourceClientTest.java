package com.vir.bm.virwebclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

class WebClientResourceClientTest {

    private final WebClient webClient = WebClient.builder().build();
    final String first_date = "2021-10-22 23:59:59";
    final String second_date = "2021-12-23 23:59:59";

    @Test
    void shouldRetrieveResourceLoadBetween() {
        WebClientResourceClient webClientResourceClient = new WebClientResourceClient(webClient);

        Flux<ResourceLoad> resourceLoadFlux = webClientResourceClient.getAllBetween(
                first_date,
                second_date);
        Assertions.assertNotNull(resourceLoadFlux);
        Assertions.assertTrue(resourceLoadFlux.take(2).count().block() > 0);


    }

}