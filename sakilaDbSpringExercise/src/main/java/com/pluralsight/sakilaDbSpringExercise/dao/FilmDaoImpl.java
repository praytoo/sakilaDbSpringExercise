package com.pluralsight.sakilaDbSpringExercise.dao;

import com.pluralsight.sakilaDbSpringExercise.model.Actor;
import com.pluralsight.sakilaDbSpringExercise.model.Film;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmDaoImpl implements FilmDao{
    private DataSource dataSource;

    public FilmDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();
        String query = "SELECT * FROM Film";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while(resultSet.next()) {
                int film_id = resultSet.getInt("film_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                int release_year = resultSet.getInt("release_year");
                int language_id = resultSet.getInt("language_id");
                int length = resultSet.getInt("length");
                String rating = resultSet.getString("rating");

                films.add(new Film(film_id, title, description, release_year, language_id, length, rating));
            }


        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return films;
    }

    @Override
    public Film getFilmById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM film WHERE film_id = ?;");
        ) {
            preparedStatement.setInt(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    Film film = new Film (resultSet.getInt("film_id"),
                    resultSet.getString("title"), resultSet.getString("description"), resultSet.getInt("release_year"), resultSet.getInt("language_id"), resultSet.getInt("length"), resultSet.getString("rating"));
                    return film;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Film addFilm(Film film) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO film (film_id, title, description, release_year, language_id, length, rating) VALUES (?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);) {

            preparedStatement.setInt(1, film.getFilm_id());
            preparedStatement.setString(2, film.getTitle());
            preparedStatement.setString(3, film.getDescription());
            preparedStatement.setInt(4, film.getRelease_year());
            preparedStatement.setInt(5, film.getLanguage_id());
            preparedStatement.setInt(6, film.getLength());
            preparedStatement.setString(7, film.getRating());

            int rows = preparedStatement.executeUpdate();

            System.out.println("Rows updated: " + rows);

            try (ResultSet keys = preparedStatement.getGeneratedKeys();) {

                boolean results = false;
                while (keys.next()) {
                    results = true;
                    System.out.println("Keys added: " + keys.getInt(1));
                }if (!results){
                    System.out.println("No results were found");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return film;
    }

    @Override
    public void updateFilm(int id, Film film) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE film SET title = ?, description = ?, release_year = ?, language_id = ?, length = ?, rating = ?" + "WHERE film_id = ?;")) {

            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setString(2, film.getDescription());
            preparedStatement.setInt(3, film.getRelease_year());
            preparedStatement.setInt(4, film.getLanguage_id());
            preparedStatement.setInt(5, film.getLength());
            preparedStatement.setString(6, film.getRating());
            preparedStatement.setInt(7, film.getFilm_id());

            int rows = preparedStatement.executeUpdate();

            System.out.println("Rows updated: " + rows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFilm(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM film WHERE film_id = ?;")) {

            preparedStatement.setInt(1, id);

            int rows = preparedStatement.executeUpdate();

            System.out.println("Rows updated: " + rows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Film getFilmByTitle(String title) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM film WHERE title = ?;");
        ) {
            preparedStatement.setString(1, title);

            try(ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    Film film = new Film(resultSet.getInt("film_id"), resultSet.getString("title"), resultSet.getString("description"), resultSet.getInt("release_year"), resultSet.getInt("language_id"), resultSet.getInt("length"), resultSet.getString("rating"));
                    return film;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
