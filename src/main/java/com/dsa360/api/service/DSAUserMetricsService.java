package com.dsa360.api.service;

public interface DSAUserMetricsService {
    long getTotalDSAUsers();
    long getActiveDSAs();
    long getDailyNewRegistrations();
    long getWeeklyNewRegistrations();
    long getMonthlyNewRegistrations();
    long getPendingRegistrations();
    long getInactiveDSAs();
}
