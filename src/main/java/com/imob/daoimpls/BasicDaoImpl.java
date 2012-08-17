package com.imob.daoimpls;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;



import com.imob.daos.BasicDao;


public class BasicDaoImpl implements BasicDao{
	private SessionFactory sessionFactory;
	
	@Value("#{imobSessionFactory}")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;	
		
	}	
	public Session getCurrentSession() { 		
		return sessionFactory.getCurrentSession();
	}
	
}
