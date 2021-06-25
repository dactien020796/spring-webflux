package com.example.springreactive.client;

import com.example.springreactive.entity.Book;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BookClient {

    private WebClient client = WebClient.create("http://localhost:8080");
    public Mono<Book> getBook(String userId){
        return client.get()
                .uri("/api/books/{userId}", userId)
                .retrieve()
                .bodyToMono(Book.class).log("Book fetched: ");
    }

    public Flux<Book> getAllBooks(){
        return client.get()
                .uri("/api/books")
                .exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(Book.class))
                //.delayElements(Duration.ofSeconds(1)) //back-pressure
                .log("Book Fetched: ");
    }
}
