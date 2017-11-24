package com.vfw5.base.dao;

import org.hibernate.Session;

/**
 * Created by sadupa on 8/6/14.
 */
public interface HibernateSessionFactory {

    Session getSession();
    Session openSession();
}
