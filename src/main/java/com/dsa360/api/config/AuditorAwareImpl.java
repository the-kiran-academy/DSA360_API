package com.dsa360.api.config;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component("auditorProvider")
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Later, fetch logged-in user from Spring Security context
        return Optional.of("SYSTEM"); // fallback/default
        
    }
}
