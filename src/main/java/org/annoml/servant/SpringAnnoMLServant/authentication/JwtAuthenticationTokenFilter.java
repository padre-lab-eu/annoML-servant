package org.annoml.servant.SpringAnnoMLServant.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Value("${authProvider.jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtAuthenticationProvider authenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String requestHeader = request.getHeader(tokenHeader);



            if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
                String authToken = requestHeader.substring(7);
                JwtAuthentication authentication = new JwtAuthentication(authToken);
                SecurityContextHolder.getContext().setAuthentication(authenticationProvider.authenticate(authentication));
            }
            chain.doFilter(request, response);
        }
    }

