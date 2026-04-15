package org.natural_selection.www;

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
public class NaturalSelectionAuthRequestFilter extends GenericFilterBean {

    private final NaturalSelectionAuthProcessor authProcessor;

    public NaturalSelectionAuthRequestFilter(NaturalSelectionAuthProcessor authProcessor) {
        this.authProcessor = authProcessor;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest)servletRequest;

        if (!request.getRequestURI().equals("/auth.txt") && !request.getRequestURI().equals("/auth/version.txt")) {
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
