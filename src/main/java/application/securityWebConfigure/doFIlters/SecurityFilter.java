package application.securityWebConfigure.doFIlters;


import application.models.UserModel;
import application.services.controller.repositoriesByAspects.Get;
import application.services.security.JwtServiceSecurity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

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
            UserModel userDetails = get.findByEmail(jwt).orElseThrow();
            var user = new UsernamePasswordAuthenticationToken(userDetails, null, null);
            SecurityContextHolder.getContext().setAuthentication(user);
        }
        ;
        filterChain.doFilter(request, response);
    }

    protected String recoverToken(HttpServletRequest servletRequest) {
        var authorization = servletRequest.getHeader("Authorization");
        if (authorization == null) return null;
        return authorization.replace("Bearer", "").replace(" ", "");
    }
}
