package com.pwx;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Thinker Peng on 2018/11/22.
 */
public class HibernateUtils
{

    private static SessionFactory sessionFactory;

    private static final ThreadLocal<Session> threadLocalSession = new ThreadLocal<>();

    // 创建sessionFactory
    static
    {
        try
        {
            // 采用默认的hibernate.cfg.xml来启动一个Configuration的实例
            Configuration cfg = new Configuration().configure();
            // 创建会话工厂对象
            sessionFactory = cfg.buildSessionFactory();
        }
        catch (Throwable e)
        {
            System.out.println("Initial SessionFactory creation failed." + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * get local thread session
     * @return session
     */
    public static Session getSession()
    {
        // 获取当前线程的session
        Session session = threadLocalSession.get();
        // 如果session为null，则从SessionFactory中获取一个session放入ThreadLocal
        if (session == null)
        {
            System.out.println("session is null.");
            session = sessionFactory.openSession();
            threadLocalSession.set(session);
        }

        return session;
    }

    /**
     * close local thread session
     */
    public static void closeSession()
    {
        Session session = threadLocalSession.get();
        if (session != null)
        {
            session.close();
            threadLocalSession.set(null);
        }
    }
}
