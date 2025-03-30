package com.linkup.api.controller;

import com.linkup.api.dto.ChatRequest;
import com.linkup.api.dto.EmprendedorDTO;
import com.linkup.api.model.Mensajes;
import com.linkup.api.service.ChatService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/usuarios")
    public ResponseEntity<List<EmprendedorDTO>> obtenerUsuariosConConversaciones() {
        List<EmprendedorDTO> emprendedores = chatService.obtenerEmprendedoresConConversaciones();
        return ResponseEntity.ok(emprendedores);
    }

    @GetMapping("/chat/{emprendedorId}/{mentorId}")
    public ResponseEntity<List<Mensajes>> obtenerMensajes(@PathVariable Long emprendedorId, @PathVariable Long mentorId) {
        return ResponseEntity.ok(chatService.obtenerMensajesPorEmprendedorYMentor(emprendedorId, mentorId));
    }

    @PostMapping("/enviarMensaje")
    public ResponseEntity<Mensajes> enviarMensaje(
            @RequestParam @NotNull Long emprendedorId,
            @RequestParam @NotNull Long mentorId,
            @RequestParam @NotNull String contenido) {
        try {
            Mensajes mensaje = chatService.enviarMensaje(emprendedorId, mentorId, contenido);
            return ResponseEntity.ok(mensaje);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/enviarMensajeComoMentor")
    public ResponseEntity<Mensajes> enviarMensajeComoMentor(
            @RequestParam @NotNull Long emprendedorId,
            @RequestParam @NotNull Long mentorId,
            @RequestParam @NotNull String contenido) {
        try {
            Mensajes mensaje = chatService.enviarMensajeComoMentor(emprendedorId, mentorId, contenido);
            return ResponseEntity.ok(mensaje);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
