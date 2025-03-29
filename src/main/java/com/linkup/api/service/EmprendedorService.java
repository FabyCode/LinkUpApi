package com.linkup.api.service;

import com.linkup.api.model.Emprendedor;
import com.linkup.api.repository.EmprendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmprendedorService {
    @Autowired
    private EmprendedorRepository emprendedorRepository;

    public Emprendedor registrarEmprendedor(Emprendedor emprendedor) {
        return emprendedorRepository.save(emprendedor);
    }

    public Emprendedor obtenerEmprendedorPorPrincipalId(String principalId) {
        return emprendedorRepository.findByPrincipalId(principalId);
    }
}
