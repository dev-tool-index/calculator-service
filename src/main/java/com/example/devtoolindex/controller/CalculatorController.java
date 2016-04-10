package com.example.devtoolindex.controller;

import com.example.devtoolindex.response.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/calc") public class CalculatorController {
    private static final String ADD = "add";

    @RequestMapping("/add") @ResponseBody
    public Result add(@RequestParam(value = "arg1", required = false, defaultValue = "0") int arg1,
        @RequestParam(value = "arg2", required = false, defaultValue = "0") int arg2) {
        // TODO cal by service
        return new Result(arg1, arg2, ADD, arg1 + arg2);
    }
}
