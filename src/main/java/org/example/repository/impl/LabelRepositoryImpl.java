package org.example.repository.impl;

import org.example.config.HibernateConf;
import org.example.model.Label;
import org.example.repository.LabelRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class LabelRepositoryImpl implements LabelRepository {

    SessionFactory sessionFactory = HibernateConf.getSessionFactory();

    @Override
    public Label get(Integer id) {
        Label label = null;
        try {
            Session session = sessionFactory.openSession();
            label = session.get(Label.class, id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return label;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            Label label = session.get(Label.class, id);
            session.remove(label);
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Label> getAll() {
        Session session = sessionFactory.openSession();
        List<Label> labels = null;
        try {
            labels = session.createQuery("from Label", Label.class).getResultList();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return labels;
    }

    @Override
    public Label update(Label label) {
        Session session = sessionFactory.openSession();
        try {
            session.refresh(label);
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return label;
    }

    @Override
    public Label create(Label label) {
        Session session = sessionFactory.openSession();
        try {
            session.persist(label);
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return label;
    }
}
