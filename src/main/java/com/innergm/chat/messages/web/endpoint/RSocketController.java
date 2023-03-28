package com.innergm.chat.messages.web.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class RSocketController {

    private final Flux<String> stream;

    @Autowired
    public RSocketController(final Flux<String> stream) {
        this.stream = stream;
    }

    @MessageMapping("my.time-updates.stream")
    public Flux<String> getTimeUpdatesStream() {
        return stream;
    }
}
