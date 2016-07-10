package com.example.devtoolindex.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RestController
public class RootController {

    @RequestMapping("/")
    @ResponseBody
    public ModelAndView root() {
        log.info("root");
        return new ModelAndView(new RedirectView("/health", true));
    }
}
