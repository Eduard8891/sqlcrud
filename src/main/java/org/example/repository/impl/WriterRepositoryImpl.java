package org.example.repository.impl;

import jakarta.transaction.Transactional;
import org.example.config.HibernateConf;
import org.example.model.Writer;
import org.example.repository.WriterRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class WriterRepositoryImpl implements WriterRepository {
    SessionFactory sessionFactory = HibernateConf.getSessionFactory();

    @Override
    public Writer get(Integer id) {
        Writer writer = null;
        try {
            Session session = sessionFactory.openSession();
            writer = session.get(Writer.class, id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return writer;
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Writer writer = session.get(Writer.class, id);
            session.remove(writer);
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Writer> getAll() {
        Session session = sessionFactory.openSession();
        List<Writer> writers = null;
        try {
            writers = session.createQuery("from Writer", Writer.class).getResultList();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return writers;
    }
    @Transactional
    @Override
    public Writer update(Writer writer) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.refresh(writer);
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return writer;
    }
    @Transactional
    @Override
    public Writer create(Writer writer) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(writer);
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return writer;
    }
}
