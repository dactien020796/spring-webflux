package com.example.springreactive.repository;

import com.example.springreactive.entity.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BookRepository extends ReactiveCrudRepository<Book, Long> {

    Mono<Book> findById(Long id);
}
