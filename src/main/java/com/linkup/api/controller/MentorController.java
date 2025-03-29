package com.linkup.api.controller;

import com.linkup.api.model.Mentor;
import com.linkup.api.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mentores")
public class MentorController {
    @Autowired
    private MentorService mentorService;

    @PostMapping
    public ResponseEntity<Mentor> crearOActualizarMentor(@RequestBody Mentor mentor) {
        Mentor mentorGuardado = mentorService.guardarMentor(mentor);
        return new ResponseEntity<>(mentorGuardado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Mentor>> obtenerTodosLosMentores() {
        List<Mentor> mentores = mentorService.obtenerTodosLosMentores();
        return new ResponseEntity<>(mentores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mentor> obtenerMentorPorId(@PathVariable Long id) {
        Optional<Mentor> mentorOpt = mentorService.obtenerMentorPorId(id);
        return mentorOpt.map(mentor -> new ResponseEntity<>(mentor, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/principalId/{principalId}")
    public ResponseEntity<Mentor> obtenerMentorPorPrincipalId(@PathVariable String principalId) {
        Mentor mentor = mentorService.obtenerMentorPorPrincipalId(principalId);
        return new ResponseEntity<>(mentor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMentor(@PathVariable Long id) {
        mentorService.eliminarMentor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
