package com.example.library.logging;

import com.example.library.book.api.BookCatalogedEvent;
import com.opencqrs.esdb.client.Event;
import com.opencqrs.framework.eventhandler.EventHandling;
import org.springframework.stereotype.Component;

@Component
public class LogHandler {

    @EventHandling("log")
    public void on(Event event) {
        System.out.println(event);
    }
}
