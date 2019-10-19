package com.yesipov.dao;

import com.yesipov.model.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class ImageDao {
    private SessionFactory sessionFactory;

    public ImageDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public long addImage(Image image) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(image);
            transaction.commit();
        }
        return image.getId();
    }

    public List<Image> getAllImages() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("from Image", Image.class)
                    .list();
        }
    }

    public Image getImageById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("from Image where id = :id", Image.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
