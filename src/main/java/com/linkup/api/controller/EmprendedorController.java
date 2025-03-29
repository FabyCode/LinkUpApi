package com.linkup.api.controller;

import com.linkup.api.model.Emprendedor;
import com.linkup.api.service.EmprendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emprendedores")
public class EmprendedorController {
    @Autowired
    private EmprendedorService emprendedorService;

    @PostMapping("/registrar")
    public Emprendedor registrarEmprendedor(@RequestBody Emprendedor emprendedor) {
        return emprendedorService.registrarEmprendedor(emprendedor);
    }

    @GetMapping("/{principalId}")
    public Emprendedor obtenerEmprendedor(@PathVariable String principalId) {
        return emprendedorService.obtenerEmprendedorPorPrincipalId(principalId);
    }
}
