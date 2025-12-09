package com.pluralsight.sakilaDbSpringExercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("actor")
public class ActorController {
        private ActorDao2 actorDao;

    public ActorController(ActorDao2 actorDao) {
        this.actorDao = actorDao;
    }

    @GetMapping
        public List<Actor> getAllActors() {

        return actorDao.getAllActors();
        }

    @GetMapping("/first/{first}")
        public List<Actor> getActorByFirstName(@PathVariable String first) {
            return actorDao.getActorByFirstName(first);
        }

    @GetMapping("/last/{last}")
    public List<Actor> getActorByLastName(@PathVariable String last) {
        return actorDao.getActorByLastName(last);
    }

}
