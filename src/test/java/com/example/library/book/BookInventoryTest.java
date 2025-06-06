package com.example.library.book;

import com.example.library.book.api.BookCatalogedEvent;
import com.example.library.book.api.CatalogBookCommand;
import com.opencqrs.framework.command.Command;
import com.opencqrs.framework.command.CommandHandlingTest;
import com.opencqrs.framework.command.CommandHandlingTestFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@CommandHandlingTest
public class BookInventoryTest {

    @Test
    public void bookSuccessfullyCataloged(@Autowired CommandHandlingTestFixture<BookInventory.Book, CatalogBookCommand, Void> fixture) {
        fixture
                .givenNothing()
                .when(new CatalogBookCommand("dskfhsdf", "Tolkien", "LOTR", 34857L))
                .expectSingleEvent(new BookCatalogedEvent("dskfhsdf", "Tolkien", "LOTR", 34857L));
    }

    @Test
    public void bookCannotBeCatalogedTwice(@Autowired CommandHandlingTestFixture<BookInventory.Book, CatalogBookCommand, Void> fixture) {
        fixture
                .given(new BookCatalogedEvent("dskfhsdf", "Tolkien", "LOTR", 34857L))
                .when(new CatalogBookCommand("dskfhsdf", "Tolkien", "LOTR", 34857L))
                .expectCommandSubjectConditionViolated(Command.SubjectCondition.PRISTINE);
    }
}
