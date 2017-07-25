package com.nerney.controllers;

import com.google.common.collect.Lists;
import com.nerney.OffenderRepository;
import com.nerney.domain.Offender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by nerney on 7/14/2017.
 */
@RestController
class OffenderController {
    private OffenderRepository repository;

    public OffenderController(OffenderRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/offenders")
    public List<Offender> offenders() {
        return Lists.newArrayList(repository.findAll());
    }
}
