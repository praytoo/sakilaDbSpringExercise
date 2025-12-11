package com.pluralsight.sakilaDbSpringExercise.controller;

import com.pluralsight.sakilaDbSpringExercise.model.Actor;
import com.pluralsight.sakilaDbSpringExercise.model.Film;
import com.pluralsight.sakilaDbSpringExercise.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("film")
public class FilmController {
    private FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }
    //http://localhost:8080/film
    @GetMapping
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }

    //http://localhost:8080/film/id/200
    @GetMapping("/id/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable int id){
        Film film = filmService.getFilmById(id);
        if (film == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(film);
    }

    // use insomnia
    @PostMapping
    public Film addFilm(@RequestBody Film film){
        return filmService.addFilm(film);
    }

    // use insomnia
    @PutMapping("{id}")
    public void updateFilm(@PathVariable int id, @RequestBody Film film){
        filmService.updateFilm(id, film);
    }

    // use insomnia
    @DeleteMapping("{id}")
    public void deleteFilm(@PathVariable int id){
        filmService.deleteFilm(id);
    }

    //http://localhost:8080/film/search/by-title?title=ACADEMY
    @GetMapping("search/by-title")
    public Film getFilmByTitle(@RequestParam (required = false) String title) {
        return filmService.getFilmByTitle(title);
    }
}
