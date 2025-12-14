package com.secj3303.dao;

import com.secj3303.model.FitnessPlan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class FitnessPlanDAOHibernate implements FitnessPlanDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public List<FitnessPlan> findAll() {
        return getCurrentSession().createQuery("from FitnessPlan", FitnessPlan.class).getResultList();
    }

    @Override
    @Transactional
    public FitnessPlan findById(int id) {
        return getCurrentSession().get(FitnessPlan.class, id);
    }

    @Override
    @Transactional
    public void save(FitnessPlan plan) {
        getCurrentSession().saveOrUpdate(plan);
    }

    @Override
    @Transactional
    public void delete(int id) {
        FitnessPlan plan = findById(id);
        if (plan != null) {
            getCurrentSession().delete(plan);
        }
    }

    @Override
    @Transactional
    public List<FitnessPlan> findPlansByTrainerId(int trainerId) {
        Query<FitnessPlan> query = getCurrentSession().createQuery(
            "from FitnessPlan fp where fp.trainer.id = :trainerId", FitnessPlan.class);
        query.setParameter("trainerId", trainerId);
        return query.getResultList();
    }
}
