package com.dsa360.api.dao;
public interface DSAUserMetricsDao {
    long getTotalDSAUsers();
    long getActiveDSAs();
    long getDailyNewRegistrations();
    long getWeeklyNewRegistrations();
    long getMonthlyNewRegistrations();
    long getPendingRegistrations();
    long getInactiveDSAs(); 
}
