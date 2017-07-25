package com.nerney.controllers;

import com.google.common.collect.Lists;
import com.nerney.OffenderRepository;
import com.nerney.domain.Offender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by nerney on 7/24/2017.
 */
@Controller
class IndexController {

    private OffenderRepository repository;

    public IndexController(OffenderRepository repository) {
        this.repository = repository;
    }

    @ModelAttribute("list")
    public List<Offender> getList() {
        return Lists.newArrayList(repository.findAll());
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
