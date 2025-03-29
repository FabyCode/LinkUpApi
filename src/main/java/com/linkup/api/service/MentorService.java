package com.linkup.api.service;

import com.linkup.api.model.Mentor;
import com.linkup.api.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MentorService {
    @Autowired
    private MentorRepository mentorRepository;

    public Mentor guardarMentor(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    public List<Mentor> obtenerTodosLosMentores() {
        return mentorRepository.findAll();
    }

    public Optional<Mentor> obtenerMentorPorId(Long id) {
        return mentorRepository.findById(id);
    }

    public Mentor obtenerMentorPorPrincipalId(String principalId) {
        return mentorRepository.findByPrincipalId(principalId);
    }

    public void eliminarMentor(Long id) {
        mentorRepository.deleteById(id);
    }
}
