package com.mycompany.conferencedemo.controllers;

import com.mycompany.conferencedemo.models.Workshop;
import com.mycompany.conferencedemo.repositories.WorkshopRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workshops")
public class WorkshopController {
    @Autowired
    private WorkshopRepository workshopRepository;

    @GetMapping
    public List<Workshop> list() {
        return workshopRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Workshop get(@PathVariable Long id) {
        return workshopRepository.getOne(id);
    }

    @PostMapping
    public Workshop create(@RequestBody final Workshop workshop) {
        return workshopRepository.saveAndFlush(workshop);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        workshopRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Workshop update(@PathVariable Long id, @RequestBody Workshop workshop) {
        Workshop existingWorkshop = workshopRepository.getOne(id);
        BeanUtils.copyProperties(workshop, existingWorkshop, "workshop_id");
        return workshopRepository.saveAndFlush(existingWorkshop);
    }
}
