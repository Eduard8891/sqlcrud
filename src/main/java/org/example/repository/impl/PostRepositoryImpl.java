package org.example.repository.impl;

import org.example.config.HibernateConf;
import org.example.model.Post;
import org.example.repository.PostRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PostRepositoryImpl implements PostRepository {
    SessionFactory sessionFactory = HibernateConf.getSessionFactory();

    @Override
    public Post get(Integer id) {
        Post post = null;
        try {
            Session session = sessionFactory.openSession();
            post = session.get(Post.class, id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return post;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            Post post = session.get(Post.class, id);
            session.remove(post);
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        Session session = sessionFactory.openSession();
        List<Post> posts = null;
        try {
            posts = session.createQuery("from Post", Post.class).getResultList();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post update(Post post) {
        Session session = sessionFactory.openSession();
        try {
            session.refresh(post);
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return post;
    }

    @Override
    public Post create(Post post) {
        Session session = sessionFactory.openSession();
        try {
            session.persist(post);
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return post;
    }
}

