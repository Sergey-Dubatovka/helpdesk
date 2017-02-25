///*
// * Copyright (c) 2012 by VeriFone, Inc.
// * All Rights Reserved.
// *
// * THIS FILE CONTAINS PROPRIETARY AND CONFIDENTIAL INFORMATION
// * AND REMAINS THE UNPUBLISHED PROPERTY OF VERIFONE, INC.
// *
// * Use, disclosure, or reproduction is prohibited
// * without prior written approval from VeriFone, Inc.
// */
//package com.pvt.dao.util;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//public class HibernateUtil {
//    private static HibernateUtil util = null;
//    private static final Logger LOG = LoggerFactory.getLogger(HibernateUtil.class);
//    private SessionFactory sessionFactory = null;
//    private final ThreadLocal<Session> sessionThreadLocal = new ThreadLocal<>();
//
//    private HibernateUtil() {
//        try {
//            Configuration configuration = new Configuration().configure();
//            /*.configure(HibernateUtil.class.getResource("/hibernate.cfg.xml"));*/
//            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
//            serviceRegistryBuilder.applySettings(configuration.getProperties());
//            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
//            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//        } catch (Throwable ex) {
//            ex.printStackTrace();
//            LOG.error("Initial SessionFactory creation failed." + ex);
//            System.exit(0);
//        }
//    }
//
//    public Session getSession() {
//        Session session = sessionThreadLocal.get();
//        if (session == null) {
//            session = sessionFactory.openSession();
//            sessionThreadLocal.set(session);
//        }
//        return session;
//    }
//
//    public static synchronized HibernateUtil getHibernateUtil() {
//        if (util == null) {
//            util = new HibernateUtil();
//        }
//        return util;
//    }
//
//    public void beginTransaction() {
//        getSession().getTransaction().begin();
//    }
//
//    public void realizeSession(){
//        Session s = (Session)sessionThreadLocal.get();
//        if (s != null) {
//            LOG.info("Close hibernate session");
//            sessionThreadLocal.remove();
//            s.close();
//        }else {
//            LOG.info("Can't close session because is does not exist");
//        }
//    }
//
//    public void commit() {
//        getSession().getTransaction().commit();
//    }
//
//    public void rollback() {
//        getSession().getTransaction().rollback();
//    }
//}
