package com.innergm.chat.appconfig;

import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import reactor.core.publisher.Mono;

import java.net.URI;

@Configuration
public class RSocketConfiguration {

    @Bean
    public Mono<RSocketRequester> rSocketRequester(
            RSocketStrategies rSocketStrategies,
            RSocketProperties rSocketProps) {
        return RSocketRequester.builder()
                .rsocketStrategies(rSocketStrategies)
                .dataMimeType(MediaType.APPLICATION_JSON)
                .metadataMimeType(MediaType.APPLICATION_JSON)
                .connectWebSocket(getURI(rSocketProps));
    }


    private URI getURI(RSocketProperties rSocketProps) {
        return URI.create(String.format("ws://localhost:%d%s", 8080, rSocketProps.getServer().getMappingPath()));
    }
}
