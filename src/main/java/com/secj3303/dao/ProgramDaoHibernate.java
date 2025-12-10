package com.secj3303.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.secj3303.model.Program;
import java.util.List;

@Repository
public class ProgramDaoHibernate implements ProgramDao {

    @Autowired
    private org.hibernate.SessionFactory sessionFactory;

    // For the time being/beginning: we explicitly open/close session
    private Session openSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<Program> findAll() {
        Session session = sessionFactory.openSession();
        List<Program> list = session
                .createQuery("FROM Program", Program.class) //this is HQL - Hibernate Query Language
                .list();
        session.close();
        return list;
    }

   @Override
    public Program findById(Integer id) {
 	//complete this
    Session session = openSession();

    return session.get(Program.class, id);
}
   @Override
    public void save(Program program) {
	//complete this
    Session session = openSession();
    session.save(program);
}

@Override
    public void delete(Integer id) {
	//complete this
    Session session = openSession();
    Program programToDelete = session.get(Program.class, id);
    if(programToDelete != null)
    session.delete(programToDelete);
}
} //end class

