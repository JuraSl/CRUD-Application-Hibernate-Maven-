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
		
		// opens a new session from the session factory and begin transaction
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// persists new Contact objects 
		Contact contact1 = new Contact("Tom", "tomBr@gmail.com", "London", "+440904277091");
		session.persist(contact1);
		
		// .save returning a generated identifier
		Contact contact2 = new Contact("Sam", "samSr@gmail.com", "London", "+440904266666");
		Serializable id = session.save(contact2);
		System.out.println("created id: " + id);
		
		// loads a new object from database
        Contact contact3 = (Contact) session.get(Contact.class, new Integer(1));
        if (contact3 == null) {
            System.out.println("There is no Contact object with id=1");
        } else {
            System.out.println("Contact3's name: " + contact3.getName());
        }
        
        // loads an object which is assumed exists
     	Contact contact4 = (Contact) session.load(Contact.class, new Integer(4));
     	System.out.println("Contact4's name: " + contact4.getName());
     	
        // updates a loaded instance of a Contact object
        Contact contact5 = (Contact) session.load(Contact.class, new Integer(5));
     	contact5.setEmail("info1@company.com");
     	contact5.setTelephone("1234567890");
     	session.update(contact5);                // not required
     	
        // updates a detached instance of a Contact object
        Contact contact6 = new Contact(3, "Jobs", "jobs@applet.com", "Cupertino", "0123456789");
     	session.update(contact6);
     	
        // deletes an object
     	Contact contact7 = new Contact();
     	contact7.setId(7);
     	session.delete(contact7);
     	
        // deletes a loaded instance of an object
     	Contact contact8 = (Contact) session.load(Contact.class, new Integer(8));
     	session.delete(contact8);
     	
        // commits the transaction and closes the session
     	session.getTransaction().commit();
     	session.close();
	}

}
