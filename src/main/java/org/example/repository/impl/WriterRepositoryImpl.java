package org.example.repository.impl;

import org.example.model.Writer;
import org.example.repository.ParserFromRS;
import org.example.repository.PostgresConnection;
import org.example.repository.WriterRepository;
import org.example.repository.gson.PostParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WriterRepositoryImpl implements WriterRepository {
    Connection connection = PostgresConnection.getConnection();

    @Override
    public Writer get(Integer id) {
        try {
            PreparedStatement p = connection.prepareStatement("select * from writers where id = ?");
            p.setInt(1, id);
            ResultSet resultSet = p.executeQuery();
            return ParserFromRS.mappingWriterFromRS(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement p = connection.prepareStatement("delete from writers where id = ?");
            p.setInt(1, id);
            p.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Writer> getAll() {
        List<Writer> writers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from writers");
            while (resultSet.next()) {
                writers.add(ParserFromRS.mappingWriterFromRS(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return writers;
    }

    @Override
    public Writer update(Writer writer) {
        try {
            PreparedStatement p = connection.prepareStatement(
                    "update writers set firstname = ?, lastname = ?, posts = ?, status = ? where id = ?");
            p.setString(1, writer.getFirstName());
            p.setString(2, writer.getLastName());
            p.setString(3, PostParser.toJson(writer.getPosts()));
            p.setString(4, writer.getStatus().name());
            p.setInt(5, writer.getId());
            return get(writer.getId());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Writer create(Writer writer) {
        try {
            PreparedStatement p = connection.prepareStatement(
                    "insert into writers (firstname, lastname, posts, status) values (?, ?, ?, ?)");
            p.setString(1, writer.getFirstName());
            p.setString(2, writer.getLastName());
            p.setString(3, PostParser.toJson(writer.getPosts()));
            p.setString(4, writer.getStatus().name());
            return get(writer.getId());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
