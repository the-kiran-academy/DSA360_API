package com.dsa360.api.daoimpl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dsa360.api.constants.ApprovalStatus;
import com.dsa360.api.constants.UserStatus;
import com.dsa360.api.dao.DSAUserMetricsDao;
import com.dsa360.api.entity.SystemUserEntity;

@Repository
@Transactional
public class DSAUserMetricsDaoImpl implements DSAUserMetricsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long getTotalDSAUsers() {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(SystemUserEntity.class);
            criteria.setProjection(Projections.rowCount());
            return (Long) criteria.uniqueResult();
        }
    }

    @Override
    public long getActiveDSAs() {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(SystemUserEntity.class);
            criteria.add(Restrictions.eq("status", UserStatus.ACTIVE.getValue()));
            criteria.setProjection(Projections.rowCount());
            return (Long) criteria.uniqueResult();
        }
    }

    @Override
    public long getDailyNewRegistrations() {
        try (Session session = sessionFactory.openSession()) {
            Date oneDayAgo = Date.from(Instant.now().minus(1, ChronoUnit.DAYS));
            Criteria criteria = session.createCriteria(SystemUserEntity.class);
            criteria.add(Restrictions.ge("createdDate", oneDayAgo));
            criteria.setProjection(Projections.rowCount());
            return (Long) criteria.uniqueResult();
        }
    }

    @Override
    public long getWeeklyNewRegistrations() {
        try (Session session = sessionFactory.openSession()) {
            Date sevenDaysAgo = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
            Criteria criteria = session.createCriteria(SystemUserEntity.class);
            criteria.add(Restrictions.ge("createdDate", sevenDaysAgo));
            criteria.setProjection(Projections.rowCount());
            return (Long) criteria.uniqueResult();
        }
    }

    @Override
    public long getMonthlyNewRegistrations() {
        try (Session session = sessionFactory.openSession()) {
            Date thirtyDaysAgo = Date.from(Instant.now().minus(30, ChronoUnit.DAYS));
            Criteria criteria = session.createCriteria(SystemUserEntity.class);
            criteria.add(Restrictions.ge("createdDate", thirtyDaysAgo));
            criteria.setProjection(Projections.rowCount());
            return (Long) criteria.uniqueResult();
        }
    }

    @Override
    public long getPendingRegistrations() {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(SystemUserEntity.class, "dsa");
            criteria.createAlias("dsa.dsaApplicationId", "app"); // join on dsaApplicationId
            criteria.add(Restrictions.eq("app.approvalStatus", ApprovalStatus.PENDING.getValue()));
            criteria.setProjection(Projections.rowCount());
            return (Long) criteria.uniqueResult();
        }
    }


    @Override
    public long getInactiveDSAs() {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(SystemUserEntity.class);
            criteria.add(Restrictions.eq("status", UserStatus.ACTIVE.getValue())); 
            criteria.setProjection(Projections.rowCount());
            return (Long) criteria.uniqueResult();
        }
    }
}
