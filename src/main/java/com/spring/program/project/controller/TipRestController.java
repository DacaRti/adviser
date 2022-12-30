package com.spring.program.project.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.program.project.entity.Tip;
import com.spring.program.project.services.TipService;

/**
 * @author DacaP
 * @version 1.0
 */
@RestController
@RequestMapping("/tips")
@CrossOrigin()
public class TipRestController {

    private final TipService tipService;

    @Autowired
    public TipRestController(TipService tipService) {
        this.tipService = tipService;
    }

    @GetMapping
    public List<Tip> getTips() {
        return tipService.findAll();
    }

    @GetMapping("/{id}")
    public Tip getTipById(@PathVariable long id) {
        return tipService.findById(id);
    }

    @PostMapping
    public Tip addNewTip(@RequestBody Tip tip) {
        tipService.create(tip);
        return tip;
    }

    @PutMapping
    public Tip updateTip(@RequestBody Tip tip) {
        tipService.update(tip);
        return tip;
    }

    @DeleteMapping("/{id}")
    public String deleteTip(@PathVariable long id) {
        tipService.remove(id);
        return "Tip with id = " + id + " was deleted";
    }

    @GetMapping("/random")
    public Tip getRandomTip() {
        List<Tip> tips = tipService.findAll();
        Random rand = new Random();
        return tips.get(rand.nextInt(tips.size()));
    }
}