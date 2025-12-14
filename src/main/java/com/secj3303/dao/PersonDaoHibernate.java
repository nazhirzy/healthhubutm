package com.secj3303.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.secj3303.model.Person;
import com.secj3303.model.Program;

@Repository
public class PersonDaoHibernate implements PersonDao {
    @Autowired
    private org.hibernate.SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.openSession();
    }

    @Override
    public Person findByUsernameAndPassword(String name, String password) {
        Session session = openSession();
        return session.createQuery(
                        "FROM Person WHERE name = :name AND password = :password",
                        Person.class
                )
                .setParameter("name", name)
                .setParameter("password", password)
                .uniqueResult();
    }

    @Override
    public void save(Person person){
        Session session = openSession();
        session.save(person);
    }

    @Override
    public Person findById(int id) {
 	
        Session session = openSession();

        return session.get(Person.class, id);
    }

    @Override
    public void delete(int id) {
	
        Session session = openSession();
        Person personToDelete = session.get(Person.class, id);
        if(personToDelete != null)
        session.delete(personToDelete);
    }

}
