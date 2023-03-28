package com.innergm.chat.messages.web.endpoint;

import com.innergm.chat.messages.application.TutorialService;
import com.innergm.chat.messages.model.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class TutorialRestController {

    @Autowired
    TutorialService tutorialService;

    @GetMapping("/tutorials")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Tutorial> getAllTutorials(@RequestParam(required = false) String title) {
        if (title == null)
            return tutorialService.findAll();
        else
            return tutorialService.findByTitleContaining(title);
    }

    @GetMapping("/tutorials/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Tutorial> getTutorialById(@PathVariable("id") String id) {
        return tutorialService.findById(id);
    }

    @PostMapping("/tutorials")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        return tutorialService.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
    }

    @PutMapping("/tutorials/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Tutorial> updateTutorial(@PathVariable("id") String id, @RequestBody Tutorial tutorial) {
        return tutorialService.update(id, tutorial);
    }

    @DeleteMapping("/tutorials/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTutorial(@PathVariable("id") String id) {
        return tutorialService.deleteById(id);
    }

    @DeleteMapping("/tutorials")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAllTutorials() {
        return tutorialService.deleteAll();
    }

    @GetMapping("/tutorials/published")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Tutorial> findByPublished() {
        return tutorialService.findByPublished(true);
    }
}
