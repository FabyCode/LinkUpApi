package com.linkup.api.service;

import com.linkup.api.dto.EmprendedorDTO;
import com.linkup.api.model.Emprendedor;
import com.linkup.api.model.Mensajes;
import com.linkup.api.model.Mentor;
import com.linkup.api.repository.EmprendedorRepository;
import com.linkup.api.repository.MensajeRepository;
import com.linkup.api.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private EmprendedorRepository emprendedorRepository;

    @Autowired
    private MentorRepository mentorRepository;

    public List<EmprendedorDTO> obtenerEmprendedoresConConversaciones() {
        // Obtén la lista de emprendedores con mensajes en el chat
        List<Long> emprendedorIds = mensajeRepository.findDistinctEmprendedorIds();
        // Filtra los emprendedores a partir de los IDs obtenidos
        return emprendedorRepository.findAllById(emprendedorIds).stream()
                .map(emprendedor -> new EmprendedorDTO(emprendedor.getId(), emprendedor.getNombreCompleto()))
                .collect(Collectors.toList());
    }

    public List<Mensajes> obtenerMensajesPorEmprendedorYMentor(Long emprendedorId, Long mentorId) {
        // Obtener mensajes entre un emprendedor y un mentor específico
        return mensajeRepository.findByMentorIdAndEmprendedorIdOrderByFechaEnvioAsc(mentorId, emprendedorId);
    }

    public Mensajes enviarMensaje(Long emprendedorId, Long mentorId, String contenido) {
        if (contenido.length() > 256 || !contenido.matches("[a-zA-Z0-9\\s]*")) {
            throw new IllegalArgumentException("El mensaje debe ser alfanumérico y tener máximo 256 caracteres.");
        }

        // Obtener emprendedor y mentor de la base de datos
        Emprendedor emprendedor = emprendedorRepository.findById(emprendedorId)
                .orElseThrow(() -> new IllegalArgumentException("Emprendedor no encontrado"));

        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new IllegalArgumentException("Mentor no encontrado"));

        // Crear el mensaje
        Mensajes mensaje = new Mensajes();
        mensaje.setContenido(contenido);
        mensaje.setEmprendedor(emprendedor);
        mensaje.setMentor(mentor);
        mensaje.setFechaEnvio(LocalDateTime.now());

        return mensajeRepository.save(mensaje);
    }

    // Enviar una respuesta del mentor a un emprendedor específico
    public Mensajes enviarMensajeComoMentor(Long emprendedorId, Long mentorId, String contenido) {
        Emprendedor emprendedor = emprendedorRepository.findById(emprendedorId)
                .orElseThrow(() -> new IllegalArgumentException("Emprendedor no encontrado"));

        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new IllegalArgumentException("Mentor no encontrado"));

        Mensajes mensaje = new Mensajes();
        mensaje.setContenido(contenido);
        mensaje.setFechaEnvio(LocalDateTime.now());
        mensaje.setEmprendedor(emprendedor);
        mensaje.setMentor(mentor);

        return mensajeRepository.save(mensaje);
    }
}
