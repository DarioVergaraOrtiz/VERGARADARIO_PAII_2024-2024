package DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import java.util.List;

public class GenericDAO<T> {
    private Class<T> type;
    private SessionFactory sessionFactory;

    public GenericDAO(Class<T> type, Class<?>[] entityClasses) {
    	this.type = type;
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        for (Class<?> entityClass : entityClasses) {
            configuration.addAnnotatedClass(entityClass);
        }
        sessionFactory = configuration.buildSessionFactory();
    }

    public void save(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        T entity = session.get(type, id);
        if (entity != null) {
            session.delete(entity);
        }
        transaction.commit();
        session.close();
    }

    public List<T> query() {
        Session session = sessionFactory.openSession();
        List<T> entities = session.createQuery("from " + type.getName()).list();
        session.close();
        return entities;
    }

    public void update(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }
}

