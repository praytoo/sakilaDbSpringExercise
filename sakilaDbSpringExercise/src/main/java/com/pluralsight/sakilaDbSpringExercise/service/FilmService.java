package com.pluralsight.sakilaDbSpringExercise.service;

import com.pluralsight.sakilaDbSpringExercise.dao.FilmDao;
import com.pluralsight.sakilaDbSpringExercise.model.Film;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    private FilmDao filmDao;

    public FilmService(FilmDao filmDao) {
        this.filmDao = filmDao;
    }
    public List<Film> getAllFilms(){
        return filmDao.getAllFilms();
    }
    public Film getFilmById(int id){
        return filmDao.getFilmById(id);
    }
    public Film addFilm(Film film){
        return filmDao.addFilm(film);
    }
    public void updateFilm(int id, Film film){
        filmDao.updateFilm(id, film);
    }
    public void deleteFilm(int id){
        filmDao.deleteFilm(id);
    }
    public Film getFilmByTitle(String title){
        return filmDao.getFilmByTitle(title);
    }
}
