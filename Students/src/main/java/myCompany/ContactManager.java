package myCompany;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class ContactManager {
	
	public static void main(String[] args) {
		
		// loads configuration and creates a new session from a session factory
		// configures settings from hibernate.cfg.xml
		Configuration configuration = new Configuration().configure();                        
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
		serviceRegistryBuilder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		
	}

}
