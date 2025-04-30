package com.dsa360.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsa360.api.dto.DSAMetricsCombinedResponse;
import com.dsa360.api.dto.DSAMetricsResponseDto;
import com.dsa360.api.dto.DSAMetricsResponseDto.NewRegistrations;
import com.dsa360.api.service.DSAUserMetricsService;

@RestController
@RequestMapping("/admin/metrics")
public class DSAUserMetricsController {

    @Autowired
    private DSAUserMetricsService dsaUserMetricsService;
    
    @GetMapping("/all")
	

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_SUB_ADMIN')")
    public ResponseEntity<DSAMetricsCombinedResponse> getAllMetrics() {
        long totalUsers = dsaUserMetricsService.getTotalDSAUsers();
        long activeDSAs = dsaUserMetricsService.getActiveDSAs();
        long daily = dsaUserMetricsService.getDailyNewRegistrations();
        long weekly = dsaUserMetricsService.getWeeklyNewRegistrations();
        long monthly = dsaUserMetricsService.getMonthlyNewRegistrations();
        long pending = dsaUserMetricsService.getPendingRegistrations();
        long inactive = dsaUserMetricsService.getInactiveDSAs();

        DSAMetricsResponseDto.NewRegistrations newRegs = new NewRegistrations(daily, weekly, monthly);
        DSAMetricsResponseDto dto = new DSAMetricsResponseDto(totalUsers, activeDSAs, newRegs, pending, inactive);

        return ResponseEntity.ok(new DSAMetricsCombinedResponse("success", dto));
    }

    @GetMapping("/total-users")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_SUB_ADMIN')")
    public ResponseEntity<Long> getTotalDSAUsers() {
        return ResponseEntity.ok(dsaUserMetricsService.getTotalDSAUsers());
    }

    @GetMapping("/active-users")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_SUB_ADMIN')")
    public ResponseEntity<Long> getActiveDSAs() {
        return ResponseEntity.ok(dsaUserMetricsService.getActiveDSAs());
    }

    @GetMapping("/inactive-users")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_SUB_ADMIN')")
    public ResponseEntity<Long> getInactiveDSAs() {
        return ResponseEntity.ok(dsaUserMetricsService.getInactiveDSAs());
    }

    @GetMapping("/new-registrations/daily")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_SUB_ADMIN')")
    public ResponseEntity<Long> getDailyNewRegistrations() {
        return ResponseEntity.ok(dsaUserMetricsService.getDailyNewRegistrations());
    }

    @GetMapping("/new-registrations/weekly")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_SUB_ADMIN')")
    public ResponseEntity<Long> getWeeklyNewRegistrations() {
        return ResponseEntity.ok(dsaUserMetricsService.getWeeklyNewRegistrations());
    }

    @GetMapping("/new-registrations/monthly")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_SUB_ADMIN')")
    public ResponseEntity<Long> getMonthlyNewRegistrations() {
        return ResponseEntity.ok(dsaUserMetricsService.getMonthlyNewRegistrations());
    }

    @GetMapping("/pending-registrations")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_SUB_ADMIN')")
    public ResponseEntity<Long> getPendingRegistrations() {
        return ResponseEntity.ok(dsaUserMetricsService.getPendingRegistrations());
    }
}
