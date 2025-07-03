package com.losung.projectmanager.service;

import com.losung.projectmanager.model.Bitacora;
import com.losung.projectmanager.repository.BitacoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BitacoraService {
    @Autowired
    private BitacoraRepository bitacoraRepository;

    public Bitacora save(Bitacora bitacora) {
        return bitacoraRepository.save(bitacora);
    }

    public List<Bitacora> findAll() {
        return bitacoraRepository.findAll();
    }

    public Bitacora findById(Long id) {
        return bitacoraRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        bitacoraRepository.deleteById(id);
    }

    public List<Bitacora> findLastN(int n) {
        return bitacoraRepository.findAll(PageRequest.of(0, n, Sort.by(Sort.Direction.DESC, "fecha"))).getContent();
    }

    // Puedes agregar más métodos según necesidades
}
