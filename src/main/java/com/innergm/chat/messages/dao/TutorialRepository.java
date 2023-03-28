package com.innergm.chat.messages.dao;

import com.innergm.chat.messages.model.Tutorial;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;

public interface TutorialRepository extends ReactiveMongoRepository<Tutorial, String> {
    Flux<Tutorial> findByPublished(boolean published);

    Flux<Tutorial> findByTitleContaining(String title);
}
