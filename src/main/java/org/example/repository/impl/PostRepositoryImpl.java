package org.example.repository.impl;

import org.example.model.Label;
import org.example.model.Post;
import org.example.repository.RepoHelper;
import org.example.repository.PostRepository;
import org.example.PostgresConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {
    Connection connection = PostgresConnection.getConnection();

    @Override
    public Post get(Integer id) {
        Post post = new Post();
        try {
            PreparedStatement p = connection.prepareStatement(
                    "select " +
                            "p.id as id," +
                            "p.content as content," +
                            "p.created as created," +
                            "p.updated as updated," +
                            "json_agg(json_build_object('id', l.id, 'name', l.name, 'status', l.status)) as labels," +
                            "p.status as status" +
                            "from posts p" +
                            "join post_labels pl on p.id = pl.post_id" +
                            "join labels l on l.id = pl.label_id" +
                            "where p.id = ?" +
                            "group by p.id"
            );
            p.setInt(1, id);
            ResultSet resultSet = p.executeQuery();
            post = RepoHelper.mappingPostFromRS(resultSet);
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
            ResultSet resultSet = statement.executeQuery(
                    "select " +
                            "p.id as id," +
                            "p.content as content," +
                            "p.created as created," +
                            "p.updated as updated," +
                            "json_agg(json_build_object('id', l.id, 'name', l.name, 'status', l.status)) as labels," +
                            "p.status as status" +
                            "from posts p" +
                            "join post_labels pl on p.id = pl.post_id" +
                            "join labels l on l.id = pl.label_id" +
                            "group by p.id"
            );
            while (resultSet.next()) {
                posts.add(RepoHelper.mappingPostFromRS(resultSet));
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
                    "update posts set content = ?, created = ?, updated = ?, status = ? where id = ?");
            p.setString(1, post.getContent());
            p.setTimestamp(2, post.getCreated());
            p.setTimestamp(3, post.getUpdated());
            p.setString(4, post.getStatus().name());
            p.setInt(5, post.getId());
            p.executeQuery();
            return get(post.getId());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Post create(Post post) {
        try {
            PreparedStatement p = connection.prepareStatement(
                    "insert into posts (content, created, updated, status) values (?, ?, ?, ?)");
            p.setString(1, post.getContent());
            p.setTimestamp(2, post.getCreated());
            p.setTimestamp(3, post.getUpdated());
            p.setString(4, post.getStatus().name());
            p.executeQuery();
            RepoHelper.createPostLabels(post);
            return post;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public List<Label> getLabels() {
        List<Label> labels = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from labels");
            while (resultSet.next()) {
                labels.add(RepoHelper.mappingLabelFromRS(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return labels;
    }
}

