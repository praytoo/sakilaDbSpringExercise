package com.pluralsight.sakilaDbSpringExercise.dao;

import com.pluralsight.sakilaDbSpringExercise.model.Actor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActorDaoImpl implements ActorDao{
    private DataSource dataSource;

    public ActorDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Actor> getAllActors(){
        List<Actor> actors = new ArrayList<>();
        String query = "SELECT * FROM Actor";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while(resultSet.next()) {
                String firstName = resultSet.getString("first_Name");
                String lastName = resultSet.getString("last_Name");

                actors.add(new Actor(firstName, lastName));
            }


        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return actors;
    }

    @Override
    public Actor getActorById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM actor WHERE actor_id = ?;");
             ) {
            preparedStatement.setInt(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    Actor actor = new Actor (resultSet.getInt("actor_id"), resultSet.getString("first_name"), resultSet.getString("last_name"));
                    return actor;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Actor> getActorByFirstName(String first){
        List<Actor> actors = new ArrayList<>();
        String query = "SELECT * FROM Actor WHERE first_Name = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) { preparedStatement.setString(1, first);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String firstName = resultSet.getString("first_Name");
                    String lastName = resultSet.getString("last_Name");

                    actors.add(new Actor(firstName, lastName));
                }
            }


        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return actors;
    }
    @Override
    public List<Actor> getActorByLastName(String last){
        List<Actor> actors = new ArrayList<>();
        String query = "SELECT * FROM Actor WHERE last_Name = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) { preparedStatement.setString(1, last);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String firstName = resultSet.getString("first_Name");
                    String lastName = resultSet.getString("last_Name");

                    actors.add(new Actor(firstName, lastName));
                }
            }

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return actors;
    }

    @Override
    public Actor addActor(Actor actor) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO actor (first_name, last_name) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);) {

            preparedStatement.setString(1, actor.getFirstName());
            preparedStatement.setString(2, actor.getLastName());

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
        return actor;
    }

    @Override
    public void updateActor(int id, Actor actor) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE actor SET first_name = ?, last_name = ?" + "WHERE actor_id = ?;")) {

            preparedStatement.setString(1, actor.getFirstName());
            preparedStatement.setString(2, actor.getLastName());
            preparedStatement.setInt(3, actor.getId());

            int rows = preparedStatement.executeUpdate();

            System.out.println("Rows updated: " + rows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteActor(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM actor WHERE actor_id = ?;")) {

            preparedStatement.setInt(1, id);

            int rows = preparedStatement.executeUpdate();

            System.out.println("Rows updated: " + rows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
