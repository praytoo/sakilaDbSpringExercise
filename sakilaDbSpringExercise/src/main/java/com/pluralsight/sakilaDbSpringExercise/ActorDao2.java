package com.pluralsight.sakilaDbSpringExercise;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActorDao2 {
    private DataSource dataSource;

    public ActorDao2(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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
}
