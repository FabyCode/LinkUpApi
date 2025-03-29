package com.linkup.api.security;

import com.linkup.api.repository.EmprendedorRepository;
import com.linkup.api.repository.MentorRepository;
import com.linkup.api.model.Emprendedor;
import com.linkup.api.model.Mentor;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class InternetIdentityFilter extends OncePerRequestFilter {

    private final MentorRepository mentorRepository;

    private final EmprendedorRepository emprendedorRepository;

    public InternetIdentityFilter(MentorRepository mentorRepository, EmprendedorRepository emprendedorRepository) {
        this.mentorRepository = mentorRepository;
        this.emprendedorRepository = emprendedorRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.replace("Bearer ", "");

            String principalId = extractPrincipalIdFromToken(token);

            if (principalId != null) {
                if (isMentor(principalId)) {
                    authenticateAsMentor(principalId);
                } else if (isEmprendedor(principalId)) {
                    authenticateAsEmprendedor(principalId);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private Boolean isMentor(String principalId) {
        return mentorRepository.existsByPrincipalId(principalId);
    }

    private Boolean isEmprendedor(String principalId) {
        return emprendedorRepository.existsByPrincipalId(principalId);
    }

    private void authenticateAsMentor(String principalId) {
        Mentor mentor = mentorRepository.findByPrincipalId(principalId);
        if (mentor != null) {
            // Configura la autenticación en el SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(new MentorAuthenticationToken(mentor));
        }
    }

    private void authenticateAsEmprendedor(String principalId) {
        Emprendedor emprendedor = emprendedorRepository.findByPrincipalId(principalId);
        if (emprendedor != null) {
            // Configura la autenticación en el SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(new EmprendedorAuthenticationToken(emprendedor));
        }
    }

    private String extractPrincipalIdFromToken(String token) {
        // Lógica para extraer principalId del token, esto dependerá de la forma en que tu token esté estructurado
        return "extractedPrincipalId";
    }
}
