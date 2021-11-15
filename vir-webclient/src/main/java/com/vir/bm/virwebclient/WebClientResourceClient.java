package com.vir.bm.virwebclient;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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
        log.info("Getting resource loads between:" + dt1 + "and" + dt2);
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

    public Mono<ResourceLoad> saveResourceLoad(ResourceLoad resourceLoad) {
        log.info("Saving resource load : " + resourceLoad);
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("localhost:8081/addResourceLoad")
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(resourceLoad), ResourceLoad.class)
                .retrieve()
                .bodyToMono(ResourceLoad.class)
                .doOnError(IOException.class, e -> log.error(e.getMessage()));
    }

}
