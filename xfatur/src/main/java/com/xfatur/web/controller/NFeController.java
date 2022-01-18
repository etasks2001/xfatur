package com.xfatur.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("nfe")
public class NFeController {

    @GetMapping
    public String openForm() {
	return "/nfe/nfe";
    }

}
