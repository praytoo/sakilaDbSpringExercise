package com.pluralsight.sakilaDbSpringExercise.dao;

import com.pluralsight.sakilaDbSpringExercise.model.Actor;

import java.util.List;

public interface ActorDao {
    List<Actor> getAllActors();
    Actor getActorById(int id);
    List<Actor> getActorByFirstName(String first);
    List<Actor> getActorByLastName(String last);
    List<Actor> getActorByFullName(String first, String last);
    Actor addActor(Actor actor);
    void updateActor(int id, Actor actor);
    void deleteActor(int id);
}
