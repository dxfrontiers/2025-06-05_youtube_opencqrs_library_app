package com.example.library.rest;

import com.example.library.book.api.CatalogBookCommand;
import com.example.library.catalog.Book;
import com.example.library.catalog.BookRepository;
import com.opencqrs.framework.command.CommandRouter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final CommandRouter commandRouter;
    private final BookRepository bookRepository;

    public BookController(CommandRouter commandRouter, BookRepository bookRepository) {
        this.commandRouter = commandRouter;
        this.bookRepository = bookRepository;
    }

    @PostMapping
    public void catalogBook(@RequestBody CatalogBookCommand request) {
        commandRouter.send(request);
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

}
