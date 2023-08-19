package configs.securityConfiguration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import services.GetMethods.Get;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    @Autowired
    protected JwtService jwtService;
    @Autowired
    Get get;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = tokenRequest(request);
        if (token != null) {
            UserModel user = get.findByEmail(jwtService.validToken(token));
            var hash = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        }
        doFilter(request, response, filterChain);
    }

    protected String tokenRequest(HttpServletRequest servlet) {
        var token = servlet.getHeader("Authorization");
        if (token == null) return null;
        return token.replace("Beares ", "");

    }
}
