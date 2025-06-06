package com.example.library.book;

import com.example.library.book.api.BookCatalogedEvent;
import com.example.library.book.api.BookCopyPurchasedEvent;
import com.example.library.book.api.CatalogBookCommand;
import com.example.library.book.api.PurchaseBookCopyCommand;
import com.opencqrs.esdb.client.Event;
import com.opencqrs.framework.command.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@CommandHandlerConfiguration
public class BookInventory {



    @CommandHandling
    public void handle(CatalogBookCommand command, CommandEventPublisher<Book> publisher) {
        publisher.publish(
                new BookCatalogedEvent(
                        command.isbn(),
                        command.author(),
                        command.title(),
                        command.pages()
                )
        );
    }

    @StateRebuilding
    public Book on(BookCatalogedEvent event) {
        return new Book(event.isbn(), 0L);
    }



    @CommandHandling
    public UUID handle(Book book, PurchaseBookCopyCommand command, CommandEventPublisher<Book> publisher) {
        if (book.numCopies() >= 100) {
            throw new IllegalStateException("TODO");
        }

        var id = UUID.randomUUID();
        publisher.publish(
                new BookCopyPurchasedEvent(
                        command.isbn(),
                        id,
                        command.price()
                )
        );

        return id;
    }

    @StateRebuilding
    public Book on(Book book, BookCopyPurchasedEvent event) {
        return book.increaseCopies();
    }

    public record Book(
            String isbn,
            Long numCopies
    ) {
        public Book increaseCopies() {
            return new Book(isbn, numCopies + 1);
        }
    }
}
