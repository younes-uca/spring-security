package com.example.msspringsecurity.filter;


import com.example.msspringsecurity.service.facade.UserService;
import com.example.msspringsecurity.service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAutorisationFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String autorization = httpServletRequest.getHeader(JwtConstant.AUTORIZATION);
        String token = null;
        String usernameFromToken = null;
        if (autorization != null && autorization.startsWith(JwtConstant.BEARER)) {
            token = autorization.substring(JwtConstant.BEARER.length());
            usernameFromToken = jwtUtil.getUsernameFromToken(token);
        }
        if (usernameFromToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(usernameFromToken);
            if (jwtUtil.validateToken(token, userDetails)) {
                jwtUtil.registerAuthenticationTokenInContext(userDetails, httpServletRequest);
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

}
