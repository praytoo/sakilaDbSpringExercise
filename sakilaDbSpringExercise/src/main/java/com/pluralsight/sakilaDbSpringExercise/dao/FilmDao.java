package com.pluralsight.sakilaDbSpringExercise.dao;

import com.pluralsight.sakilaDbSpringExercise.model.Film;

import java.util.List;

public interface FilmDao {
    List<Film> getAllFilms();
    Film getFilmById(int id);
    Film addFilm(Film film);
    void updateFilm(int id, Film film);
    void deleteFilm(int id);
}
