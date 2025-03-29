/*package com.linkup.api.security;

import com.linkup.api.model.Emprendedor;
import com.linkup.api.model.Mentor;
import com.linkup.api.repository.EmprendedorRepository;
import com.linkup.api.repository.MentorRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmprendedorRepository emprendedorRepository;

    @Autowired
    private MentorRepository mentorRepository;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.replace("Bearer ", "");
            try {
                String subject = tokenService.getSubject(token); // Obtener el principalId del token
                if (subject != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    // Intentamos buscar un Mentor
                    Mentor mentor = mentorRepository.findByPrincipalId(subject);
                    if (mentor != null && tokenService.validateToken(token, mentor)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                mentor, null, mentor.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        // Intentamos buscar un Emprendedor
                        Emprendedor emprendedor = emprendedorRepository.findByPrincipalId(subject);
                        if (emprendedor != null && tokenService.validateToken(token, emprendedor)) {
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                    emprendedor, null, emprendedor.getAuthorities());
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("Error de autenticación: " + e.getMessage(), e);
            }
        }
        filterChain.doFilter(request, response);
    }
}
*/