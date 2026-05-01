package phronesis;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * This allows us to override a 403 response. A 403 should really be override in something that implements AccessDeniedHandler
 * but apparently SpringBoot takes some 401 responses and rethrows them as 403 responses.
 */
public class UnauthorisedAccessHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        if (request.getRequestURI().equals("/auth.txt") || request.getRequestURI().equals("/auth/version.txt") || request.getRequestURI().equals("/auth.php") || request.getRequestURI().equals("/auth/")) {
            response.setContentType("text/plain");
            response.setStatus(403);
            response.getWriter().write("Bad credentials");
        }

        // TODO: anything to do here? ideally just fall back to what SpringBoot does and only override when we need to
    }
}
