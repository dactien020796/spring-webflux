package com.example.springreactive.controller;

import com.example.springreactive.client.BookClient;
import com.example.springreactive.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client/books")
public class BookClientController {

    @Autowired
    private BookClient bookClient;

    @GetMapping("/{userId}")
    public Mono<ResponseEntity<Book>> getUserById(@PathVariable String bookId){
        Mono<Book> user = bookClient.getBook(bookId);
        return user.map(u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<Book> getAllUsers(){
        return bookClient.getAllBooks();
    }
}
