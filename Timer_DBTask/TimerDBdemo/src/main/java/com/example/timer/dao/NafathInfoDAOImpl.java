package com.example.timer.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.example.timer.model.NafathInfo;

@Repository
public class NafathInfoDAOImpl implements NafathInfoDAO {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<NafathInfo> get() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<NafathInfo> query = currentSession.createQuery("from NafathInfo", NafathInfo.class);
        return query.getResultList();
    }

    @Override
    public NafathInfo get(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        NafathInfo employeeObj = currentSession.get(NafathInfo.class, id);
        return employeeObj;
    }

    @Override
    public void save(NafathInfo employee) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(employee);
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        NafathInfo employeeObj = currentSession.get(NafathInfo.class, id);
        currentSession.delete(employeeObj);
    }
}
