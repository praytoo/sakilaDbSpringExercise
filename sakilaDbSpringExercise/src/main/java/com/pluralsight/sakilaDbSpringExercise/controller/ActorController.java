package com.pluralsight.sakilaDbSpringExercise.controller;

import com.pluralsight.sakilaDbSpringExercise.dao.ActorDao;
import com.pluralsight.sakilaDbSpringExercise.model.Actor;
import com.pluralsight.sakilaDbSpringExercise.service.ActorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("actor")
public class ActorController {
        private ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
        public List<Actor> getAllActors() {
        return actorService.getAllActors();
        }

    @GetMapping("/first/{first}")
        public List<Actor> getActorByFirstName(@PathVariable String first) {
            return actorService.getActorByFirstName(first);
        }

    @GetMapping("/last/{last}")
    public List<Actor> getActorByLastName(@PathVariable String last) {
        return actorService.getActorByLastName(last);
    }

    @GetMapping("/id/{id}")
    public Actor getActorById(@PathVariable int id){
        return actorService.getActorById(id);
    }

    @PostMapping
    public Actor addActor(@RequestBody Actor actor){
        return actorService.addActor(actor);
    }

    @PutMapping("{id}")
    public void updateActor(@PathVariable int id, @RequestBody Actor actor){
        actorService.updateActor(id, actor);
    }

    @DeleteMapping("{id}")
    public void deleteActor(@PathVariable int id){
        actorService.deleteActor(id);
    }
}
