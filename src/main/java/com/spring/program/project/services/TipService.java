package com.spring.program.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.program.project.entity.Tip;
import com.spring.program.project.repository.TipRepository;

/**
 * @author DacaP
 * @version 1.0
 */
@Service
public class TipService {
    
    private final TipRepository tipRepository;

    @Autowired
    public TipService(TipRepository tipRepository) {
        this.tipRepository = tipRepository;
    }
    
    public void create(Tip tip) {
       tipRepository.save(tip);
    }

    public List<Tip> findAll() {
        return tipRepository.findAll();
    }

    public Tip findById(long id) {
        Tip tip = null;
        Optional<Tip> optional = tipRepository.findById(id);
        if (optional.isPresent()) {
            tip = optional.get();
        }
        return tip;
    }

    public void update(Tip role) {
        tipRepository.save(role);
    }

    public void remove(long id) {
        tipRepository.deleteById(id);
    }
}
