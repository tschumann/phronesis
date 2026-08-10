package com.teamfortress.www;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class TeamFortress2AuthRequestFilter extends GenericFilterBean {

    private final TeamFortress2AuthProcessor authProcessor;

    public TeamFortress2AuthRequestFilter(TeamFortress2AuthProcessor authProcessor) {
        this.authProcessor = authProcessor;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest)servletRequest;

        if (!request.getRequestURI().equals("/ISDK/GetEquipment/v0001") && !request.getRequestURI().equals("/ISDK/GetInventory/v0001")) {
            filterChain.doFilter(servletRequest, servletResponse);

            return;
        }

        Authentication authentication;

        try {
            authentication = authProcessor.authenticate(request);

            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        catch (Exception e) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
