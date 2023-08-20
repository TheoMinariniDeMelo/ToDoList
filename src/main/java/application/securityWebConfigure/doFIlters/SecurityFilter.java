package application.securityWebConfigure.doFIlters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import application.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;*/
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import application.services.controller.Get;
import application.services.security.JwtServiceSecurity;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    JwtServiceSecurity jwtServiceSecurity;
    @Autowired
    Get get;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        if (token != null) {
            String jwt = jwtServiceSecurity.jwtVerifyToken(token);
            Optional<UserModel> userDetails = get.findByEmail(jwt);
            var user = new UsernamePasswordAuthenticationToken(userDetails, null, null);
        }
        filterChain.doFilter(request, response);
    }

    protected String recoverToken(HttpServletRequest servletRequest) {
        var authorization = servletRequest.getHeader("Authorization");
        if (authorization == null) return null;
        return authorization.replace("Bearer", "");
    }
}
