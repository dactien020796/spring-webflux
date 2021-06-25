package com.example.springreactive.service;

import com.example.springreactive.entity.Book;
import com.example.springreactive.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Flux<Book> getAll() {
        return bookRepository.findAll();
    }

    public Mono<Book> getById(Long id) {
        return bookRepository.findById(id);
    }

    public Mono<Book> create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    public Mono<Book> update(Long id, Book newBook) {
        return bookRepository.findById(id)
            .flatMap(book -> {
                book.setAuthor(newBook.getAuthor());
                book.setTitle(newBook.getTitle());
                return bookRepository.save(book);
            });
    }

    public boolean deleteById(Long id) {
        try {
            bookRepository.deleteById(id).block(); // Note this!
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
