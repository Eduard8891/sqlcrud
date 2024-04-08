package org.example.repository.impl;

import org.example.model.Writer;
import org.example.repository.RepoHelper;
import org.example.PostgresConnection;
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
            PreparedStatement p = connection.prepareStatement(
                    "select" +
                            "w.id as id," +
                            "w.firstname as firstname," +
                            "w.lastname as lastname," +
                            "json_agg(json_build_object('id', p.id, " +
                            "'content', p.content, " +
                            "'created', p.created, " +
                            "'updated', p.updated, " +
                            "'labels', (select json_agg(json_build_object('id', id, 'name', name, 'status', status)) from labels group by id)," +
                            "'status', p.status)) as posts," +
                            "w.status as status" +
                            "from writers w" +
                            "join writer_posts wp on w.id = wp.writer_id" +
                            "join posts p on p.id = wp.post_id" +
                            "where w.id = ?" +
                            "group by w.id"
            );
            p.setInt(1, id);
            ResultSet resultSet = p.executeQuery();
            return RepoHelper.mappingWriterFromRS(resultSet);
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
            ResultSet resultSet = statement.executeQuery(
                    "select" +
                            "w.id as id," +
                            "w.firstname as firstname," +
                            "w.lastname as lastname," +
                            "json_agg(json_build_object('id', p.id, " +
                            "'content', p.content, " +
                            "'created', p.created, " +
                            "'updated', p.updated, " +
                            "'labels', (select json_agg(json_build_object('id', id, 'name', name, 'status', status)) from labels group by id)," +
                            "'status', p.status)) as posts," +
                            "w.status as status" +
                            "from writers w" +
                            "join writer_posts wp on w.id = wp.writer_id" +
                            "join posts p on p.id = wp.post_id" +
                            "group by w.id");
            while (resultSet.next()) {
                writers.add(RepoHelper.mappingWriterFromRS(resultSet));
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
                    "update writers set firstname = ?, lastname = ?, status = ? where id = ?");
            p.setString(1, writer.getFirstName());
            p.setString(2, writer.getLastName());
            p.setString(4, writer.getStatus().name());
            p.setInt(4, writer.getId());
            return writer;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Writer create(Writer writer) {
        try {
            PreparedStatement p = connection.prepareStatement(
                    "insert into writers (firstname, lastname, status) values (?, ?, ?)");
            p.setString(1, writer.getFirstName());
            p.setString(2, writer.getLastName());
            p.setString(3, writer.getStatus().name());
            RepoHelper.createWriterPosts(writer);
            return writer;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
