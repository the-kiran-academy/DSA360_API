package com.dsa360.api.dto;

public class DSAMetricsResponseDto {
    private long totalUsers;
    private long activeDSAs;
    private NewRegistrations newRegistrations;
    private long pendingRegistrations;
    private long inactiveDSAs;

    // Inner DTO for registration breakdown
    public static class NewRegistrations {
        private long daily;
        private long weekly;
        private long monthly;

        public NewRegistrations(long daily, long weekly, long monthly) {
            this.daily = daily;
            this.weekly = weekly;
            this.monthly = monthly;
        }

        public long getDaily() { return daily; }
        public long getWeekly() { return weekly; }
        public long getMonthly() { return monthly; }
    }

    public DSAMetricsResponseDto(long totalUsers, long activeDSAs,
                                 NewRegistrations newRegistrations,
                                 long pendingRegistrations,
                                 long inactiveDSAs) {
        this.totalUsers = totalUsers;
        this.activeDSAs = activeDSAs;
        this.newRegistrations = newRegistrations;
        this.pendingRegistrations = pendingRegistrations;
        this.inactiveDSAs = inactiveDSAs;
    }

    public long getTotalUsers() { return totalUsers; }
    public long getActiveDSAs() { return activeDSAs; }
    public NewRegistrations getNewRegistrations() { return newRegistrations; }
    public long getPendingRegistrations() { return pendingRegistrations; }
    public long getInactiveDSAs() { return inactiveDSAs; }
}
