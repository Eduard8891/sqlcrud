package org.example.repository;

import org.example.PostgresConnection;
import org.example.model.Label;
import org.example.model.Post;
import org.example.model.PostStatus;
import org.example.model.Writer;
import org.example.repository.gson.LabelsParser;
import org.example.repository.gson.PostParser;

import java.sql.*;
import java.util.List;

public class RepoHelper {

    private static Connection connection = PostgresConnection.getConnection();

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

    public static void createPostLabels(Post post) {
        List<Integer> labelIds = post.getLabels().stream().map(Label::getId).toList();
        for (int current : labelIds) {
            try {
                PreparedStatement p = connection.prepareStatement("insert into post_labels (post_id, label_id) values (?, ?)");
                p.setInt(1, post.getId());
                p.setInt(2, current);
                p.executeQuery();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void createWriterPosts(Writer writer) {
        List<Integer> postIds = writer.getPosts().stream().map(Post::getId).toList();
        for (int current : postIds) {
            try {
                PreparedStatement p = connection.prepareStatement("insert into writer_posts (writer_id, post_id) values (?, ?)");
                p.setInt(1, writer.getId());
                p.setInt(2, current);
                p.executeQuery();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}
