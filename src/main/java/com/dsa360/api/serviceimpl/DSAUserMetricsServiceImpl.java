package com.dsa360.api.serviceimpl;

import com.dsa360.api.dao.DSAUserMetricsDao;
import com.dsa360.api.service.DSAUserMetricsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DSAUserMetricsServiceImpl implements DSAUserMetricsService {

    @Autowired
    private DSAUserMetricsDao dsaUserMetricsDao;

    @Override
    public long getTotalDSAUsers() {
        return dsaUserMetricsDao.getTotalDSAUsers();
    }

    @Override
    public long getActiveDSAs() {
        return dsaUserMetricsDao.getActiveDSAs();
    }

    @Override
    public long getDailyNewRegistrations() {
        return dsaUserMetricsDao.getDailyNewRegistrations();
    }

    @Override
    public long getWeeklyNewRegistrations() {
        return dsaUserMetricsDao.getWeeklyNewRegistrations();
    }

    @Override
    public long getMonthlyNewRegistrations() {
        return dsaUserMetricsDao.getMonthlyNewRegistrations();
    }

    @Override
    public long getPendingRegistrations() {
        return dsaUserMetricsDao.getPendingRegistrations();
    }

    @Override
    public long getInactiveDSAs() {
        return dsaUserMetricsDao.getInactiveDSAs();
    }
}
