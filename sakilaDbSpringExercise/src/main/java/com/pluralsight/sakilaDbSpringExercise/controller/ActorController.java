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

    //http://localhost:8080/actor
    @GetMapping
        public List<Actor> getAllActors() {
        return actorService.getAllActors();
        }

    //http://localhost:8080/actor/search/by-first?first=Penelope
    @GetMapping("search/by-first")
        public List<Actor> getActorByFirstName(@RequestParam (required = false) String first) {
            return actorService.getActorByFirstName(first);
        }

    //http://localhost:8080/actor/search/by-last?last=Guiness
    @GetMapping("search/by-last")
    public List<Actor> getActorByLastName(@RequestParam (required = false) String last) {
        return actorService.getActorByLastName(last);
    }

    //http://localhost:8080/actor/search?first=penelope&last=guiness
    @GetMapping("search")
    public List<Actor> getActorFullName(@RequestParam (required = false) String first, @RequestParam (required = false) String last){
        return  actorService.getActorByFullName(first, last);
    }

    //http://localhost:8080/actor/id/200
    @GetMapping("/id/{id}")
    public Actor getActorById(@PathVariable int id){
        return actorService.getActorById(id);
    }

    // use insomnia
    @PostMapping
    public Actor addActor(@RequestBody Actor actor){
        return actorService.addActor(actor);
    }

    // use insomnia
    @PutMapping("{id}")
    public void updateActor(@PathVariable int id, @RequestBody Actor actor){
        actorService.updateActor(id, actor);
    }

    // use insomnia
    @DeleteMapping("{id}")
    public void deleteActor(@PathVariable int id){
        actorService.deleteActor(id);
    }
}
