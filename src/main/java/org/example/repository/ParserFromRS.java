package org.example.repository;

import org.example.model.Label;
import org.example.model.Post;
import org.example.model.PostStatus;
import org.example.model.Writer;
import org.example.repository.gson.LabelsParser;
import org.example.repository.gson.PostParser;

import java.sql.*;
import java.util.List;

public class ParserFromRS {

    public static Label mappingLabelFromRS(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            PostStatus postStatus = PostStatus.valueOf(resultSet.getString("status"));
            return new Label(id, name, postStatus);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static Post mappingPostFromRS(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            String content = resultSet.getString("content");
            Timestamp created = resultSet.getTimestamp("created");
            Timestamp updated = resultSet.getTimestamp("updated");
            List<Label> labels = LabelsParser.toList(resultSet.getString("labels"));
            PostStatus status = PostStatus.valueOf(resultSet.getString("status"));
            return new Post(id, content, created, updated, labels, status);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

//    public static List<Label> parseToJavaListLabelsFromRS(Array array) {
//        try {
//            Object[] arrayObj = (Object[]) array.getArray();
//            return Arrays.stream(arrayObj).map(it -> (Label) it).toList();
//        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//        }
//        return null;
//    }

    public static Writer mappingWriterFromRS(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("firstname");
            String lastName = resultSet.getString("lastname");
            List<Post> posts = PostParser.toList(resultSet.getString("posts"));
            PostStatus status = PostStatus.valueOf(resultSet.getString("status"));
            return new Writer(id, firstName, lastName, posts, status);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

//    private static List<Post> parseToJavaListPostsFromRS(Array posts) {
//        try {
//            Object[] arrayObj = (Object[]) posts.getArray();
//            return Arrays.stream(arrayObj).map(it -> (Post) it).toList();
//        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//        }
//        return null;
//    }
}
