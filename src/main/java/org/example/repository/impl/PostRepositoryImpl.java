package org.example.repository.impl;

import org.example.model.Post;
import org.example.repository.ParserFromRS;
import org.example.repository.PostRepository;
import org.example.PostgresConnection;
import org.example.repository.gson.LabelsParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {
    Connection connection = PostgresConnection.getConnection();

    @Override
    public Post get(Integer id) {
        Post post = new Post();
        try {
            PreparedStatement p = connection.prepareStatement("select * from posts where id = ?");
            p.setInt(1, id);
            ResultSet resultSet = p.executeQuery();
            post = ParserFromRS.mappingPostFromRS(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return post;
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement p = connection.prepareStatement("delete from posts where id = ?");
            p.setInt(1, id);
            p.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from posts");
            while (resultSet.next()) {
                posts.add(ParserFromRS.mappingPostFromRS(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post update(Post post) {
        try {
            PreparedStatement p = connection.prepareStatement(
                    "update posts set content = ?, created = ?, updated = ?, labels = ?, status = ? where id = ?");
            p.setString(1, post.getContent());
            p.setTimestamp(2, post.getCreated());
            p.setTimestamp(3, post.getUpdated());
            p.setString(4, LabelsParser.toJson(post.getLabels()));
            p.setString(5, post.getStatus().name());
            p.setInt(6, post.getId());
            p.executeQuery();
            return get(post.getId());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Post create(Post post) {
        Post result = null;
        try {
            PreparedStatement p = connection.prepareStatement(
                    "insert into posts (content, created, updated, labels, status) values (?, ?, ?, ?, ?)");
            p.setString(1, post.getContent());
            p.setTimestamp(2, post.getCreated());
            p.setTimestamp(3, post.getUpdated());
            p.setString(4, LabelsParser.toJson(post.getLabels()));
            p.setString(5, post.getStatus().name());
            ResultSet resultSet = p.executeQuery();
            result = ParserFromRS.mappingPostFromRS(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }
}

