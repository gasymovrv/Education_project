package hibertest.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static SessionFactory getSessionFactory(InheritanceStrategy is) {
        switch (is) {
            case MAPPED_SUPERCLASS:
                return new Configuration()
                        .configure("hibernate.cfg1.xml")
                        .buildSessionFactory();
            case TABLE_PER_CLASS:
                return new Configuration()
                        .configure("hibernate.cfg2.xml")
                        .buildSessionFactory();
            case SINGLE_TABLE:
                return new Configuration()
                        .configure("hibernate.cfg3.xml")
                        .buildSessionFactory();
            case JOINED_TABLE:
                return new Configuration()
                        .configure("hibernate.cfg4.xml")
                        .buildSessionFactory();
        }
        return null;
    }

    public enum InheritanceStrategy {
        MAPPED_SUPERCLASS, TABLE_PER_CLASS, SINGLE_TABLE, JOINED_TABLE
    }
}