package com.imob.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface BasicDao {
	public void setSessionFactory(SessionFactory sessionFactory);
	public Session getCurrentSession();
}
