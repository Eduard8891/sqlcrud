package org.example.repository.impl;

import org.example.model.Label;
import org.example.repository.LabelRepository;
import org.example.repository.ParserFromRS;
import org.example.PostgresConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LabelRepositoryImpl implements LabelRepository {
    Connection connection = PostgresConnection.getConnection();

    @Override
    public Label get(Integer id) {
        Label label = null;
        try {
            PreparedStatement p = connection.prepareStatement("select * from labels where id = ?");
            p.setInt(1, id);
            ResultSet resultSet = p.executeQuery();
            label = ParserFromRS.mappingLabelFromRS(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return label;
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement p = connection.prepareStatement("delete from labels where id = ?");
            p.setInt(1, id);
            p.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Label> getAll() {
        List<Label> labels = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from labels");
            while (resultSet.next()) {
                labels.add(ParserFromRS.mappingLabelFromRS(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return labels;
    }

    @Override
    public Label update(Label label) {
        Label result = null;
        try {
            PreparedStatement p = connection.prepareStatement("update labels set name = ?, status = ? where id = ?");
            p.setString(1, label.getName());
            p.setString(2, label.getStatus().name());
            p.setInt(3, label.getId());
            ResultSet resultSet = p.executeQuery();
            result = ParserFromRS.mappingLabelFromRS(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    @Override
    public Label create(Label label) {
        Label result = null;
        try {
            PreparedStatement p = connection.prepareStatement("insert into labels (id, name, status) values (?, ?, ?)");
            p.setInt(1, label.getId());
            p.setString(2, label.getName());
            p.setString(3, label.getStatus().name());
            ResultSet resultSet = p.executeQuery();
            connection.commit();
            result = ParserFromRS.mappingLabelFromRS(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }
}
