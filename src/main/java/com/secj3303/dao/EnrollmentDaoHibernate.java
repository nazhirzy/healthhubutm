package com.secj3303.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.secj3303.model.Enrollment;
@Repository
public class EnrollmentDaoHibernate implements EnrollmentDao{
    @Autowired
    private org.hibernate.SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.openSession();
    }
    
    @Override
    public void save(Enrollment enrollment) {
        Session session = openSession();
        session.save(enrollment);
    }

    @Override
    public List<Enrollment> findByMemberId(int memberId) {
        Session session = openSession();

        List<Enrollment> list = session
            .createQuery("FROM Enrollment e WHERE e.member.id = :memberId", Enrollment.class)
            .setParameter("memberId", memberId)
            .list();

        return list;
    }
}
