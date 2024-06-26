package org.example.config;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateConf {

    private static final SessionFactory sf;

    static {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        sf = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sf;
    }
}
