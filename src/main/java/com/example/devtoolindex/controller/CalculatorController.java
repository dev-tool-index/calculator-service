package com.example.devtoolindex.controller;

import com.example.devtoolindex.db.service.ArithmeticService;
import com.example.devtoolindex.response.CalcResult;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/calc") public class CalculatorController {
    private static final String ADD = "add";

    @Setter @Autowired ArithmeticService arithmeticService;
    @RequestMapping("/add") @ResponseBody
    public CalcResult add(@RequestParam(value = "arg1", required = false, defaultValue = "0") int arg1,
        @RequestParam(value = "arg2", required = false, defaultValue = "0") int arg2) {
        int result = arithmeticService.add(arg1, arg2);
        return new CalcResult(arg1, arg2, ADD, result);
    }
}
