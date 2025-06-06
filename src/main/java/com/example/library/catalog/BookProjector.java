package com.example.library.catalog;

import com.example.library.book.api.BookCatalogedEvent;
import com.opencqrs.framework.eventhandler.EventHandling;
import org.springframework.stereotype.Component;

@Component
public class BookProjector {

    private final BookRepository bookRepository;

    public BookProjector(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventHandling("books")
    public void on(BookCatalogedEvent event) {
        var book = new Book();
        book.setIsbn(event.isbn());
        book.setAuthor(event.author());
        book.setTitle(event.title());
        bookRepository.save(book);
    }

}
