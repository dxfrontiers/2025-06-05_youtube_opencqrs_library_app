package com.example.library.book.api;

import com.opencqrs.framework.command.Command;

import java.util.UUID;

public record PurchaseBookCopyCommand(
        String isbn,
        Double price
) implements Command {

    @Override
    public String getSubject() {
        return "/books/" + isbn();
    }

    @Override
    public SubjectCondition getSubjectCondition() {
        return SubjectCondition.EXISTS;
    }
}
