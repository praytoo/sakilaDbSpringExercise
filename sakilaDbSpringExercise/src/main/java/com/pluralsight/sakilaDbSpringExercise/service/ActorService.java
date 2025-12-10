package com.pluralsight.sakilaDbSpringExercise.service;

import com.pluralsight.sakilaDbSpringExercise.dao.ActorDao;
import com.pluralsight.sakilaDbSpringExercise.model.Actor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {
    private ActorDao actorDao;

    public ActorService(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    public List<Actor> getAllActors() {
        return actorDao.getAllActors();
    }

    public List<Actor> getActorByFirstName(String firstName) {
        return actorDao.getActorByFirstName(firstName);
    }

    public List<Actor> getActorByLastName(String lastName) {
        return actorDao.getActorByLastName(lastName);
    }

    public Actor addActor(Actor actor) {
        return actorDao.addActor(actor);
    }

    public Actor getActorById(int id) {
        return actorDao.getActorById(id);
    }

    public void updateActor(int id, Actor actor) {
        Actor actorToUpdate = getActorById(id);
        if(actor.getFirstName() != null) {
            actorToUpdate.setFirstName(actor.getFirstName());
        }
        if(actor.getLastName() != null) {
            actorToUpdate.setLastName(actor.getLastName());
        }
        actorDao.updateActor(id, actorToUpdate);
    }

    public void deleteActor(int id) {
        actorDao.deleteActor(id);
    }
}
