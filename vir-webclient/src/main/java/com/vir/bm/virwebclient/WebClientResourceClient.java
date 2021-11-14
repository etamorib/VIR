package com.vir.bm.virwebclient;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.io.IOException;
import java.time.LocalDateTime;

@Log4j2
public class WebClientResourceClient {

    private WebClient webClient;

    public WebClientResourceClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<ResourceLoad> getAllBetween(String dt1, String dt2) {
        log.info("DATE:" + dt1);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("localhost:8081/getResourceLoadsByTimeInterval")
                        .queryParam("first_date", dt1)
                        .queryParam("second_date", dt2)
                        .build())
                .retrieve()
                .bodyToFlux(ResourceLoad.class)
                .retryWhen(Retry.maxInARow(5))
                .doOnError(IOException.class, e -> log.error(e.getMessage()));
    }

}
